package com.example.route.data.contarct

import com.example.route.data.database.model.CategoryDto
import kotlinx.coroutines.flow.Flow

interface CategoryRepo {

    suspend fun getAllCategories(): Flow<List<CategoryDto>>
    suspend fun insertCategory(category: CategoryDto)
    suspend fun deleteCategory(category: CategoryDto)
}