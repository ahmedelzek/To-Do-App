package com.example.route.data.usecase

import com.example.route.data.contarct.CategoryRepo
import com.example.route.data.database.model.CategoryDto
import com.example.route.data.database.model.TaskDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(private val categoryRepo: CategoryRepo) {

    suspend fun invoke(): Flow<List<CategoryDto>> {
        return categoryRepo.getAllCategories()
    }}