package com.example.finalapplicationclass

import androidx.lifecycle.ViewModel
import com.example.finalapplicationclass.model.Student

class StudentsListVIewModel : ViewModel() {

    private var _students: List<Student>? = null
    var students: List<Student>?
        get() = students
        private set(value) {
            _students = value
        }

    fun set(students: List<Student>?) {
        this.students = students
    }
}