package com.example.finalapplicationclass.model

import android.graphics.Bitmap
import android.media.Image

typealias StudentsCallback = (List<Student>) -> Unit
typealias EmptyCallback = () -> Unit

interface GetAllStudentsListener {
    fun onCompletion(students: List<Student>)
}

/*
1. Create FireStore model - Done
2. Set and Get - Done
3. Integrate FireStore - Done
4. Integrate Students - Done
5. Integrate Storage
*/

class Model private constructor(){

    private val firebaseModel = FirebaseModel()
    private val cloudinaryModel = CloudinaryModel()

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback: StudentsCallback) {
        firebaseModel.getAllStudents(callback)
    }

    fun add(student: Student, image: Bitmap?, callback: EmptyCallback) {
        firebaseModel.add(student) {
            image?.let {
                uploadImageToFirebase(it, student.id){ uri ->
                    if (!uri.isNullOrBlank()){
                        val st = student.copy(avatarUrl = uri)
                        firebaseModel.add(st, callback)
                    } else {
                        callback()
                    }
                }
            } ?: callback()
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {
        firebaseModel.delete(student, callback)
    }

    fun uploadImageToFirebase(image: Bitmap, name: String, callback: (String?) -> Unit) {
        firebaseModel.uploadImage(image, name, callback)
    }
}