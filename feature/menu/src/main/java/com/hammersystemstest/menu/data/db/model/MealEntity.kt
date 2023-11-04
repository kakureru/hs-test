package com.hammersystemstest.menu.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hammersystemstest.menu.domain.Category
import com.hammersystemstest.menu.domain.Meal

@Entity(tableName = "meals")
class MealEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    @ColumnInfo(name = "category_name") val categoryName: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String,
)

fun MealEntity.toDomain(category: Category) = Meal(
    id = id,
    name = name,
    description = description,
    price = price,
    category = category,
    thumbnailUrl = thumbnailUrl,
)