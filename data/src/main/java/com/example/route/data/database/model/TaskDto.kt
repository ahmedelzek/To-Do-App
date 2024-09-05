package com.example.route.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TaskDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    var taskTitle: String?,
    @ColumnInfo
    var dateAsString: String?,
    @ColumnInfo
    var date: Long?,
    @ColumnInfo
    var isDone: Boolean?
): Serializable