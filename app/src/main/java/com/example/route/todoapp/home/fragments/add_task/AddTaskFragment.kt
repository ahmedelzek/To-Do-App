package com.example.route.todoapp.home.fragments.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.route.data.database.model.CategoryDto
import com.example.route.data.database.model.TaskDto
import com.example.route.todoapp.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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


        viewModel.getCategories()

        observeCategories()

        binding.fabAddTask.setOnClickListener {
            val task = createTaskFromInput()
            viewModel.addTask(task)
            onAddClick.invoke()
            dismiss()
        }

        viewModel.getCategories()
    }

    private fun createTaskFromInput(): TaskDto {

        val task = binding.taskEditText.text.toString()
        val date = currentDate
        val dateAsString = currentDate.toString()
        val isDone = false
        var taskCategory = binding.categoryTextView.text.toString()
        if(binding.categoryTextView.text.toString() == "No Category"){
            taskCategory = ""
        }
        return TaskDto(taskTitle = task, taskCategory = taskCategory, dateAsString = dateAsString, date = date?.toEpochDay(), isDone = isDone)
    }
    private fun observeCategories() {
        lifecycleScope.launch {
            viewModel.categories.collect { categories ->
                binding.categoryTextView.setOnClickListener { categoryView ->
                    showCategoryMenu(categoryView, categories)
                }
            }
        }
    }

    private fun showCategoryMenu(view: View, categories: List<CategoryDto>) {
        val popupMenu = PopupMenu(requireContext(), view)

        categories.forEach { category ->
            popupMenu.menu.add(category.categoryName)
        }

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            binding.categoryTextView.text = menuItem.title // Update TextView
            true
        }
        popupMenu.show()
    }

}