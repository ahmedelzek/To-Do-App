package com.example.route.data.usecase

import com.example.route.data.contarct.TaskRepo
import javax.inject.Inject

class GetTasksByCategoryUseCas @Inject constructor(private val taskRepo: TaskRepo) {

    suspend operator fun invoke(category: String) = taskRepo.getTasksByCategory(category)
}