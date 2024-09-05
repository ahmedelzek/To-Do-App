package com.example.route.data.usecase

import com.example.route.data.contarct.TaskRepo
import com.example.route.data.database.model.TaskDto
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val repo: TaskRepo) {

    suspend fun invoke(task: TaskDto) {
        repo.updateTask(task)
    }
}