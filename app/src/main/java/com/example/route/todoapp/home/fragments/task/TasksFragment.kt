package com.example.route.todoapp.home.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.forEach
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
        showTaps()
    }

    private fun showAddTaskFragment() {
        val bottomSheetFragment = AddTaskFragment {
            // Handle the action when "Add" is clicked in the bottom sheet
        }
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }
    private fun showTaps() {

        val tab = binding.tabLayout.newTab()
        tab.text = "All"
        tab.tag = "All"
        val tab2 = binding.tabLayout.newTab()
        tab2.text = "None"
        tab2.tag = "None"
        binding.tabLayout.addTab(tab)
        binding.tabLayout.addTab(tab2)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
