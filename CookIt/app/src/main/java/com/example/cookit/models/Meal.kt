package com.example.cookit.models

import com.example.cookit.constants.Constants
import com.google.gson.annotations.SerializedName

data class MealRoot(val meals: List<Meal>)

data class Meal(
    @SerializedName(Constants.MEAL_ID)
    val id: String,
    @SerializedName(Constants.MEAL_NAME)
    val name: String,
    @SerializedName(Constants.MEAL_IMAGE)
    val image: String,

    @SerializedName(Constants.MEAL_INSTRUCTIONS)
    val instructions: String,
    @SerializedName(Constants.MEAL_VIDEO)
    val video: String
)
