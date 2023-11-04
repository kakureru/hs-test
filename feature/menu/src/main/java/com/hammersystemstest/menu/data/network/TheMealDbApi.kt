package com.hammersystemstest.menu.data.network

import com.hammersystemstest.menu.data.network.response.CategoriesResponse
import com.hammersystemstest.menu.data.network.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TheMealDbApi {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") categoryName: String,
    ): MealsResponse
}