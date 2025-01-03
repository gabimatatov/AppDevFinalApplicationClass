package com.example.finalapplicationclass.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val name: String,
    val id: String,
    val avatarUrl: String,
    var isChecked: Boolean
)
