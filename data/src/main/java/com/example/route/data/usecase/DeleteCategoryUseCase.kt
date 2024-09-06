package com.example.route.data.usecase

import com.example.route.data.contarct.CategoryRepo
import com.example.route.data.database.model.CategoryDto
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(private val categoryRepo: CategoryRepo)  {

    suspend fun invoke(category: CategoryDto) {
        categoryRepo.deleteCategory(category)
    }

}