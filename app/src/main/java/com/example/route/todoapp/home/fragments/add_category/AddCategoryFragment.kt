package com.example.route.todoapp.home.fragments.add_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.route.data.database.model.CategoryDto
import com.example.route.todoapp.databinding.FragmentAddCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryFragment(private val onAddClick: () -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddCategoryBinding
    private val viewModel: AddCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCategoryBtn.setOnClickListener {
            val category = createCategoryFromInput()
            viewModel.addCategory(category)
            onAddClick.invoke()
            dismiss()
        }
    }

    private fun createCategoryFromInput(): CategoryDto {
        val categoryName = binding.categoryEditText.text.toString()
        return CategoryDto(categoryName = categoryName)
    }
}