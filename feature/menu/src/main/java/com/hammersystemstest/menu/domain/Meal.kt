package com.hammersystemstest.menu.domain

class Meal(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val category: Category,
    val thumbnailUrl: String,
)