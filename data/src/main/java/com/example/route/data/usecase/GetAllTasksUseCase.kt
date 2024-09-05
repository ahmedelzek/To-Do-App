package com.example.route.data.usecase

import com.example.route.data.contarct.TaskRepo
import com.example.route.data.database.model.TaskDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repository: TaskRepo
) {
    suspend fun invoke(): Flow<List<TaskDto>> {
        return repository.getAllTasks()
    }
}
