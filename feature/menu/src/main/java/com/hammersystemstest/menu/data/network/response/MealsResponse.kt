package com.hammersystemstest.menu.data.network.response

import com.google.gson.annotations.SerializedName
import com.hammersystemstest.menu.data.network.dto.MealDto

class MealsResponse(
    @SerializedName("meals") val meals: List<MealDto>,
)