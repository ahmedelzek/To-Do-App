package com.example.route.todoapp.home.fragments.add_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.route.data.database.model.CategoryDto
import com.example.route.data.usecase.AddCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase) :
    ViewModel() {

        fun addCategory(category: CategoryDto) {
        viewModelScope.launch(Dispatchers.IO) {
            addCategoryUseCase.invoke(category)
        }
    }

}