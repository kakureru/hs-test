package com.hammersystemstest.menu.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hammersystemstest.menu.data.db.model.CategoryEntity
import com.hammersystemstest.menu.data.db.model.MealEntity

@Dao
interface MenuDao {

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM meals WHERE category_name = :categoryName")
    suspend fun getMealsByCategory(categoryName: String): List<MealEntity>

    @Transaction
    suspend fun updateCategories(categories: List<CategoryEntity>) {
        clearCategories()
        saveCategories(categories)
    }

    @Query("DELETE FROM categories")
    suspend fun clearCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(categories: List<CategoryEntity>)

    @Transaction
    suspend fun updateMeals(meals: List<MealEntity>, categoryName: String) {
        clearMeals(categoryName)
        saveMeals(meals)
    }

    @Query("DELETE FROM meals WHERE category_name = :categoryName")
    suspend fun clearMeals(categoryName: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMeals(meals: List<MealEntity>)
}