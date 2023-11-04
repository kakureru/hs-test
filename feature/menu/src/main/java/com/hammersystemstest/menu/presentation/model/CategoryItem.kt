package com.hammersystemstest.menu.presentation.model

import com.hammersystemstest.menu.domain.Category

data class CategoryItem(
    val id: Int,
    val name: String,
)

fun Category.toUi() = CategoryItem(
    id = id,
    name = name,
)

fun CategoryItem.toDomain() = Category(id, name)