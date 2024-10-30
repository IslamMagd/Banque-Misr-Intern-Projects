package com.example.cookit.apis

import androidx.compose.ui.unit.Constraints
import com.example.cookit.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealsApiService {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable: MealApiCallable by lazy {
        retrofit.create(MealApiCallable::class.java)
    }

}