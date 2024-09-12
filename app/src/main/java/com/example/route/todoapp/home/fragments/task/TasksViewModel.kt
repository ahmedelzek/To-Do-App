package com.example.route.todoapp.home.fragments.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.route.data.database.model.CategoryDto
import com.example.route.data.database.model.TaskDto
import com.example.route.data.usecase.DeleteCategoryUseCase
import com.example.route.data.usecase.DeleteTaskUseCase
import com.example.route.data.usecase.GetAllCategoriesUseCase
import com.example.route.data.usecase.GetAllTasksUseCase
import com.example.route.data.usecase.GetTasksByCategoryUseCas
import com.example.route.data.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getCategoriesUseCase: GetAllCategoriesUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getTasksByCategoryUseCas: GetTasksByCategoryUseCas
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskDto>>(emptyList())
    private val _categories = MutableStateFlow<List<CategoryDto>>(emptyList())

    val categories: StateFlow<List<CategoryDto>> = _categories.asStateFlow()
    val tasks: StateFlow<List<TaskDto>> = _tasks.asStateFlow()



    fun loadAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllTasksUseCase.invoke()
                .collect { tasks ->
                    _tasks.value = tasks
                }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke().collect { categories ->
                _categories.value = categories
            }
        }
    }

    fun getTasksByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getTasksByCategoryUseCas.invoke(category).collect { tasks ->
                _tasks.value = tasks
            }
        }
    }

    fun updateTask(task: TaskDto) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.invoke(task)
        }
    }

    fun deleteTask(task: TaskDto) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.invoke(task)
        }
    }
    fun deleteCategory(category: CategoryDto) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCategoryUseCase.invoke(category)
        }
    }
}
