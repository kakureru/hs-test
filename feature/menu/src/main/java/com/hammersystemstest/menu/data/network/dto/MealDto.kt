package com.hammersystemstest.menu.data.network.dto

import com.google.gson.annotations.SerializedName
import com.hammersystemstest.menu.data.db.model.MealEntity
import com.hammersystemstest.menu.domain.Category
import com.hammersystemstest.menu.domain.Meal
import kotlin.random.Random

class MealDto(
    @SerializedName("idMeal") val id: Int,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnailUrl: String,
)

fun MealDto.toDomain(category: Category) = Meal(
    id = id,
    name = name,
    thumbnailUrl = thumbnailUrl,
    description = "Sample description",
    price = 123,
    category = category
)

fun MealDto.toDb(category: Category) = MealEntity(
    id = id,
    name = name,
    description = "Sample description",
    price = 123,
    categoryName = category.name,
    thumbnailUrl = thumbnailUrl,
)