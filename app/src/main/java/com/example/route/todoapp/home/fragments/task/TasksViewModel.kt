package com.example.route.todoapp.home.fragments.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.route.data.database.model.TaskDto
import com.example.route.data.usecase.DeleteTaskUseCase
import com.example.route.data.usecase.GetAllTasksUseCase
import com.example.route.data.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskDto>>(emptyList())
    val tasks: StateFlow<List<TaskDto>> = _tasks.asStateFlow()



    fun loadAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllTasksUseCase.invoke()
                .collect { tasks ->
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
}
