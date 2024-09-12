package com.example.route.data.contarct

import com.example.route.data.database.model.TaskDto
import kotlinx.coroutines.flow.Flow

interface TaskRepo {

    suspend fun getAllTasks(): Flow<List<TaskDto>>
    suspend fun getTaskByDate(date: Long): Flow<List<TaskDto>>
    suspend fun insertTask(task: TaskDto)
    suspend fun deleteTask(task: TaskDto)
    suspend fun updateTask(task: TaskDto)
    suspend fun getTasksByCategory(category: String): Flow<List<TaskDto>>
}
