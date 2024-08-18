package com.example.route.todoapp.home.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.route.todoapp.R
import com.example.route.todoapp.databinding.FragmentTasksBinding
import com.example.route.todoapp.home.fragments.add_task.AddTaskFragment

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            showAddTaskFragment()
        }
    }

    private fun showAddTaskFragment() {
        val bottomSheetFragment = AddTaskFragment {
            // Handle the action when "Add" is clicked in the bottom sheet
        }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
