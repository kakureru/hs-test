package com.hammersystemstest.menu.data.network.dto

import com.google.gson.annotations.SerializedName
import com.hammersystemstest.menu.data.db.model.CategoryEntity
import com.hammersystemstest.menu.domain.Category

class CategoryDto(
    @SerializedName("idCategory") val id: Int,
    @SerializedName("strCategory") val strCategory: String,
)

fun CategoryDto.toDomain() = Category(
    id = id,
    name = strCategory,
)

fun CategoryDto.toDb() = CategoryEntity(
    id = id,
    name = strCategory,
)