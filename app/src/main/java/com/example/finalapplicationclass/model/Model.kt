package com.example.finalapplicationclass.model

class Model private constructor(){

    val students: MutableList<Student> = ArrayList()
    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..20){
            val student = Student(
                name = "Name Ben Shapiro $i",
                id = "Student ID: 5678$i",
                avatarUrl = "",
                isChecked = false
            )
            students.add(student)
        }
    }
}