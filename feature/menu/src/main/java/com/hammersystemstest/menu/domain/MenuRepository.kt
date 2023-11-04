package com.hammersystemstest.menu.domain

import kotlinx.coroutines.flow.Flow

internal interface MenuRepository {
    suspend fun getBannerUrls(): List<String>
    suspend fun getCategories(): Flow<List<Category>>
    suspend fun getMealsByCategory(category: Category): Flow<List<Meal>>
}