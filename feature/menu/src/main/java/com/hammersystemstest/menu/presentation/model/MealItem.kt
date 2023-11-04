package com.hammersystemstest.menu.presentation.model

import com.hammersystemstest.menu.domain.Meal

class MealItem(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: Int,
)

fun Meal.toUI() = MealItem(
    id = id,
    imageUrl = thumbnailUrl,
    title = name,
    description = description,
    price = price,
)