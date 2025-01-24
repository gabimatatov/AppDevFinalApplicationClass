package com.example.finalapplicationclass

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.finalapplicationclass.model.Model
import com.example.finalapplicationclass.model.Student

class StudentsListViewModel : ViewModel() {
    var students: LiveData<List<Student>> = Model.shared.students
}
