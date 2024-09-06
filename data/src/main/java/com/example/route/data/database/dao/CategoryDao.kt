package com.example.route.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.route.data.database.model.CategoryDto

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: CategoryDto)

    @Delete
    fun delete(category: CategoryDto)

    @Query("SELECT * FROM CategoryDto")
    fun showAllCategories(): List<CategoryDto>
}