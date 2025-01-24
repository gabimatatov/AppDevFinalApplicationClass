package com.example.finalapplicationclass.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalapplicationclass.model.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAllStudents() : LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE id = :id")
    fun getStudentById(id: String) : Student

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudents(vararg student: Student)

    @Delete
    fun delete(student: Student)
}