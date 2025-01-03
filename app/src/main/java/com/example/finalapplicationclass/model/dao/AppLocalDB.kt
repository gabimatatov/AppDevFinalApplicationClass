package com.example.finalapplicationclass.model.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalapplicationclass.base.MyApplication
import com.example.finalapplicationclass.model.Student

@Database(entities = [Student::class], version = 1)
abstract class AppLocalDBRepository : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

class AppLocalDB {
    val database: AppLocalDBRepository by lazy {

        val context = MyApplication.Globals.context ?: throw IllegalStateException("Application context is missing")

        Room.databaseBuilder(
            context = context,
            klass = AppLocalDBRepository::class.java,
            name = "dbFileName.db"
        ).fallbackToDestructiveMigration().build()
    }
}