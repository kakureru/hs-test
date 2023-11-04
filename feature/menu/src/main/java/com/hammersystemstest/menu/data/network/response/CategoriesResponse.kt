package com.hammersystemstest.menu.data.network.response

import com.google.gson.annotations.SerializedName
import com.hammersystemstest.menu.data.network.dto.CategoryDto

class CategoriesResponse(
    @SerializedName("categories") val categories: List<CategoryDto>
)