package com.example.finalapplicationclass.model

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.finalapplicationclass.model.dao.AppLocalDB
import com.example.finalapplicationclass.model.dao.AppLocalDBRepository
import java.util.concurrent.Executors


typealias StudentsCallback = (List<Student>) -> Unit
typealias EmptyCallback = () -> Unit

interface GetAllStudentsListener {
    fun onCompletion(students: List<Student>)
}

class Model private constructor(){

    private val database: AppLocalDBRepository = AppLocalDB.database
    private val executor =  Executors.newSingleThreadExecutor()
    private val mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        executor.execute {
            val students = database.studentDao().getAllStudents()
            Thread.sleep(4000)
            mainHandler.post {
                callback(students)
            }
        }
    }

    fun add(student: Student, callback: EmptyCallback) {
        executor.execute {
            database.studentDao().insertStudents(student)
            Thread.sleep(4000)
            mainHandler.post {
                callback()
            }
        }
    }
}