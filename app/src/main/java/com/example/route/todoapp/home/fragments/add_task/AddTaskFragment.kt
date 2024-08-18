package com.example.route.todoapp.home.fragments.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.route.todoapp.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskFragment(private val onAddClick: () -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private var selectDate = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}