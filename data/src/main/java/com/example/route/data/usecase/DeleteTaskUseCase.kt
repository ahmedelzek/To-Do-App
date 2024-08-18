package com.example.route.data.usecase

import com.example.route.data.contarct.TaskRepo
import com.example.route.data.database.model.TaskDto
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepo
) {
    suspend operator fun invoke(task: TaskDto) {
        repository.deleteTask(task)
    }
}
