package com.example.finalapplicationclass.model

import android.graphics.Bitmap
import android.os.Looper
import androidx.core.os.HandlerCompat
import androidx.lifecycle.LiveData
import com.example.finalapplicationclass.model.dao.AppLocalDB
import com.example.finalapplicationclass.model.dao.AppLocalDBRepository
import java.util.concurrent.Executors

typealias StudentsCallback = (List<Student>) -> Unit
typealias EmptyCallback = () -> Unit

interface GetAllStudentsListener {
    fun onCompletion(students: List<Student>)
}

class Model private constructor(){

    enum class Storage {
        FIREBASE,
        CLOUDINARY
    }

    private val firebaseModel = FirebaseModel()
    private val cloudinaryModel = CloudinaryModel()

    companion object {
        val shared = Model()
    }

    private val database: AppLocalDBRepository = AppLocalDB.database
    private val executor = Executors.newSingleThreadExecutor()
    private var mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    val students: LiveData<List<Student>>
        get() = database.studentDao().getAllStudents()

    fun getAllStudents(): LiveData<List<Student>> {
        return students ?: database.studentDao().getAllStudents()
    }

    fun refreshStudents() {
        val lastUpdated: Long = Student.lastUpdated
        firebaseModel.getAllStudents(lastUpdated) { list ->
            executor.execute {
                var latestTime = lastUpdated
                for (student in list) {
                    database.studentDao().insertStudents(student)
                    student.lastUpdated?.let {
                        if (latestTime < it) {
                            latestTime = it
                        }
                    }
                }
                Student.lastUpdated = latestTime
            }
        }
    }

    fun add(student: Student, image: Bitmap?, storage: Storage, callback: EmptyCallback) {
        firebaseModel.add(student) {
            image?.let {
                uploadTo(
                    storage,
                    image = image,
                    name = student.id,
                    callback = { uri ->
                        if (!uri.isNullOrBlank()) {
                            val st = student.copy(avatarUrl = uri)
                            firebaseModel.add(st, callback)
                        } else {
                            callback()
                        }
                    },
                )
            } ?: callback()
        }
    }

    private fun uploadTo(storage: Storage, image: Bitmap, name: String, callback: (String?) -> Unit) {
        when (storage) {
            Storage.FIREBASE -> {
                uploadImageToFirebase(image, name, callback)
            }
            Storage.CLOUDINARY -> {
                uploadImageToCloudinary(
                    bitmap = image,
                    name = name,
                    onSuccess = callback,
                    onError = { callback(null) }
                )
            }
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {
        firebaseModel.delete(student, callback)
    }

    private fun uploadImageToFirebase(image: Bitmap, name: String, callback: (String?) -> Unit) {
        firebaseModel.uploadImage(image, name, callback)
    }

    private fun uploadImageToCloudinary(bitmap: Bitmap, name: String, onSuccess: (String?) -> Unit, onError: (String?) -> Unit) {
        cloudinaryModel.uploadImage(bitmap, name, onSuccess, onError)
    }
}