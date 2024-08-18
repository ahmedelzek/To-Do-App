package com.example.route.data.repo

import com.example.route.data.contarct.TaskRepo
import com.example.route.data.database.dao.TaskDao
import com.example.route.data.database.model.TaskDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepoImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepo {

    override suspend fun getAllTasks(): Flow<List<TaskDto>> {
        return flow {
            emit(taskDao.showAllTasks())
        }
    }

    override suspend fun getTaskByDate(date: Long): Flow<List<TaskDto>>  {
        return flow {
            emit(taskDao.getTasksByDate(date))
        }
    }

    override suspend fun insertTask(task: TaskDto) {
        taskDao.insert(task)
    }

    override suspend fun deleteTask(task: TaskDto) {
        taskDao.delete(task)
    }
}
