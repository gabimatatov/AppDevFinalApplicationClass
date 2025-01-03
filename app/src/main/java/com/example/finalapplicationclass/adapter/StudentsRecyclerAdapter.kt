package com.example.finalapplicationclass.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.finalapplicationclass.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapplicationclass.R
import com.example.finalapplicationclass.model.Student

class StudentsRecyclerAdapter(private var students: List<Student>?): RecyclerView.Adapter<StudentViewHolder>() {

    var listener: OnItemClickListener? = null

    fun set(students: List<Student>?) {
        this.students = students
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflation = LayoutInflater.from(parent.context)
        val view = inflation.inflate(
            R.layout.student_list_row,
            parent,
            false
        )
        return StudentViewHolder(view, listener)
    }

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students?.get(position), position)
    }
}