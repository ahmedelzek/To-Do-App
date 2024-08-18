package com.example.route.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.route.data.database.model.TaskDto

@Dao
interface TaskDao {
    @Insert
    fun insert(task: TaskDto)

    @Delete
    fun delete(task: TaskDto)

    @Update
    fun update(task: TaskDto)

    @Query("SELECT * FROM TaskDto")
    fun showAllTasks(): List<TaskDto>

    @Query("SELECT * FROM TaskDto WHERE date = :date")
    fun getTasksByDate(date: Long): List<TaskDto>
}