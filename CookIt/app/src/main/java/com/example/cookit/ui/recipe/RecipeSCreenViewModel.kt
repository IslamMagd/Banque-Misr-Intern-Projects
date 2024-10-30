package com.example.cookit.ui.recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookit.apis.MealsApiService
import com.example.cookit.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeSCreenViewModel: ViewModel() {

    private var _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    private var _hasError = MutableStateFlow<Boolean>(false)
    val hasError = _hasError.asStateFlow()

    fun getRecipe(mealId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _meals.update {
                    MealsApiService.callable.getRecipe(mealId).meals
                }
                _hasError.update { false }
            } catch (e: Exception){
                Log.d("trace","Error: ${e.message}")
                _hasError.update { true }
            }
        }
    }
}