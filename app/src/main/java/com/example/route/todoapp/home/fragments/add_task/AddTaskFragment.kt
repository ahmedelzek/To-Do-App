package com.example.route.todoapp.home.fragments.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.route.data.database.model.TaskDto
import com.example.route.todoapp.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AddTaskFragment(private val onAddClick: () -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private val currentDate: LocalDate? = LocalDate.now()
    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddTask.setOnClickListener {
            val task = createTaskFromInput()
            viewModel.addTask(task)
            onAddClick.invoke()
            dismiss()
        }
    }

    private fun createTaskFromInput(): TaskDto {

        val task = binding.taskEditText.text.toString()
        val date = currentDate
        val dateAsString = currentDate.toString()
        val isDone = false
        return TaskDto(taskTitle = task, taskCategory = "", dateAsString = dateAsString, date = date?.toEpochDay(), isDone = isDone)
    }

}