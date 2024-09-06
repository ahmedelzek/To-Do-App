package com.example.route.data.usecase

import com.example.route.data.contarct.CategoryRepo
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(private val categoryRepo: CategoryRepo) {

    suspend fun invoke() = categoryRepo.getAllCategories()
}