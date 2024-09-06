package com.example.route.data.repo

import com.example.route.data.contarct.CategoryRepo
import com.example.route.data.database.dao.CategoryDao
import com.example.route.data.database.model.CategoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepoImpl @Inject constructor(private val categoryDao: CategoryDao): CategoryRepo {
    override suspend fun getAllCategories(): Flow<List<CategoryDto>> {
        return flow {
            emit(categoryDao.showAllCategories())
        }
    }

    override suspend fun insertCategory(category: CategoryDto) {
        categoryDao.insert(category)
    }

    override suspend fun deleteCategory(category: CategoryDto) {
        categoryDao.delete(category)
    }
}