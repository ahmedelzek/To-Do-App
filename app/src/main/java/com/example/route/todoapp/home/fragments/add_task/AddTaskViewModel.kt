package com.example.route.todoapp.home.fragments.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.route.data.database.model.TaskDto
import com.example.route.data.usecase.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    fun addTask(task: TaskDto) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase(task)
        }
    }
}
