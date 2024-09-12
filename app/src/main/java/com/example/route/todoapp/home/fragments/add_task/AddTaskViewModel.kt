package com.example.route.todoapp.home.fragments.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.route.data.database.model.CategoryDto
import com.example.route.data.database.model.TaskDto
import com.example.route.data.usecase.AddTaskUseCase
import com.example.route.data.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryDto>>(emptyList())

    val categories: StateFlow<List<CategoryDto>> = _categories.asStateFlow()

    fun addTask(task: TaskDto) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase(task)
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke().collect { categories ->
                _categories.value = categories
            }
        }
    }
}
