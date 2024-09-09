package com.example.route.todoapp.home.fragments.task

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.route.data.database.model.CategoryDto
import com.example.route.data.database.model.TaskDto
import com.example.route.todoapp.databinding.FragmentTasksBinding
import com.example.route.todoapp.home.fragments.add_category.AddCategoryFragment
import com.example.route.todoapp.home.fragments.add_task.AddTaskFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TasksFragment : Fragment(), TaskAdapter.OnDeleteClickListener,
    TaskAdapter.OnDoneClickListener {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    private var adapterTask = TaskAdapter(listOf())
    private val viewModel: TasksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewTasks.adapter = adapterTask
        binding.fab.setOnClickListener {
            showAddTaskFragment()
        }
        binding.addCategory.setOnClickListener {
            showAddCategoryFragment()
        }
        adapterTask.setOnDeleteClickListener(this)
        adapterTask.setOnDoneClickListener(this)
        viewModel.loadAllTasks()
        viewModel.getCategories()
        observeTasks()
        observeCategories()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAllTasks()
        viewModel.getCategories()
        observeTasks()
        observeCategories()
    }

    private fun observeTasks() {
        lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                adapterTask.updateTasksList(tasks)
            }
        }
    }

    private fun observeCategories() {
        lifecycleScope.launch {
            viewModel.categories.collect { categories ->
                setupTabs(categories)  // Dynamically set up tabs based on the categories
            }
        }
    }

    private fun showAddTaskFragment() {
        val bottomSheetFragment = AddTaskFragment {
            viewModel.loadAllTasks()
        }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun showAddCategoryFragment() {
        val bottomSheetFragment = AddCategoryFragment {
            lifecycleScope.launch {
                viewModel.getCategories()
            }
        }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun setupTabs(categories: List<CategoryDto>) {
        binding.tabLayout.removeAllTabs()
        val firstTab = binding.tabLayout.newTab()
        firstTab.text = "All"
        binding.tabLayout.addTab(firstTab)

        categories.forEach { category ->
            val tab = binding.tabLayout.newTab().apply {
                text = category.categoryName
            }
            binding.tabLayout.addTab(tab)

            binding.tabLayout.getTabAt(binding.tabLayout.tabCount - 1)?.view?.setOnLongClickListener {
                showDeleteCategoryConfirmationDialog(category)
                true
            }
        }

        binding.tabLayout.getTabAt(0)?.select()
        tabMargin()
    }

    private fun tabMargin() {
        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        tabs.forEach {
            val layoutParams = it.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = 20
            it.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }

    private fun showDeleteCategoryConfirmationDialog(category: CategoryDto) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Category")
        builder.setMessage("Are you sure you want to delete the category '${category.categoryName}'? This will delete all associated tasks as well.")

        builder.setPositiveButton("Delete") { _, _ ->
            lifecycleScope.launch {
                viewModel.deleteCategory(category)
                viewModel.getCategories()
                Toast.makeText(requireContext(), "Category '${category.categoryName}' deleted", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(task: TaskDto) {
        lifecycleScope.launch {
            viewModel.deleteTask(task)
            Toast.makeText(requireContext(), "Task ${task.taskTitle} Deleted", Toast.LENGTH_LONG)
                .show()
        }
        viewModel.loadAllTasks()
    }

    override fun onDoneClick(task: TaskDto) {
        lifecycleScope.launch {
            viewModel.updateTask(task)
            task.isDone = !task.isDone!!
        }
        viewModel.loadAllTasks()
        if (task.isDone == false)
            Toast.makeText(requireContext(), "Task ${task.taskTitle} Done", Toast.LENGTH_LONG)
                .show()
    }
}
