package com.example.route.todoapp.home.fragments.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.route.data.database.model.TaskDto
import com.example.route.todoapp.R
import com.example.route.todoapp.databinding.TaskItemBinding

class TaskAdapter(private var taskList: List<TaskDto>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    fun updateTasksList(newTasksList: List<TaskDto>) {
        taskList = newTasksList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: TaskItemBinding) :
        ViewHolder(binding.root) {
        fun bind(task: TaskDto) {

            binding.task = task
            binding.executePendingBindings()
            binding.apply {
                title.text = task.taskTitle
            }
            onDeleteClickListener?.let { onDeleteClickListener ->
                binding.deleteBtn.setOnClickListener {
                    onDeleteClickListener.onDeleteClick(task)
                }
            }
            onDoneClickListener?.let { onDoneClickListener ->
                binding.btnTaskIsDone.setOnClickListener {
                    onDoneClickListener.onDoneClick(task)
                }
            }
            onTaskClickListener?.let { onTaskClickListener ->


            }
            if (task.isDone!!) {
                binding.apply {
                    title.setTextColor(ContextCompat.getColor(title.context,R.color.blue))
                }
            }
        }
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(task: TaskDto)
    }

    private var onDeleteClickListener: OnDeleteClickListener? = null
    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        onDeleteClickListener = listener
    }

    interface OnDoneClickListener {
        fun onDoneClick(task: TaskDto)
    }

    private var onDoneClickListener: OnDoneClickListener? = null
    fun setOnDoneClickListener(listener: OnDoneClickListener) {
        onDoneClickListener = listener
    }

    interface OnTaskClickListener {
        fun onItemClick(task: TaskDto)
    }

    private var onTaskClickListener: OnTaskClickListener? = null
    fun setOnItemClickListener(listener: OnTaskClickListener) {
        onTaskClickListener = listener
    }

}