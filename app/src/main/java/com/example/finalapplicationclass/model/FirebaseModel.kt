package com.example.finalapplicationclass.model

import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import com.example.finalapplicationclass.base.Constants

class FirebaseModel {

    private val database = Firebase.firestore

    init {
        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings {  })
        }
        database.firestoreSettings = settings
    }

    fun getAllStudents(callback: StudentsCallback) {
        database.collection(Constants.Collections.STUDENTS).get().addOnCompleteListener {
            when (it.isSuccessful){
                true -> {
                    val students: MutableList<Student> = mutableListOf()
                    for (json in it.result) {
                        students.add(Student.fromJSON(json.data))
                    }
                    callback(students)
                }

                false -> {
                    callback(listOf())
                }
            }
        }
    }

    fun delete(student: Student, callback: EmptyCallback) {

    }

    fun add(student: Student, callback: EmptyCallback) {
        database.collection(Constants.Collections.STUDENTS).document(student.id).set(student.json)
            .addOnCompleteListener {
                callback()
            }
    }

}