package com.hammersystemstest.menu.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hammersystemstest.menu.domain.Category

@Entity(tableName = "categories")
class CategoryEntity(
    @PrimaryKey val name: String,
    val id: Int,
)

fun CategoryEntity.toDomain() = Category(
    id = id,
    name = name,
)