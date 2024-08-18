package com.example.route.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.route.data.database.dao.TaskDao
import com.example.route.data.database.model.TaskDto

@Database(entities = [TaskDto::class], version = 1, exportSchema = true)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}