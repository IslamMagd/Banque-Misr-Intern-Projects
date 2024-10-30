package com.example.cookit.ui.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookit.apis.MealsApiService
import com.example.cookit.models.Category
import com.example.cookit.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryScreenViewModel: ViewModel() {
    private var _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    private var _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    private var _hasError = MutableStateFlow<Boolean>(false)
    val hasError = _hasError.asStateFlow()

    init {
        getCategories()
    }

   private fun getCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _categories.update {
                    MealsApiService.callable.getCategories().categories
                }
                _hasError.update { false }
            } catch (e: Exception){
                Log.d("trace","Error${e.message}")
                _hasError.update { true }
            }
        }
    }

    fun getCategoryMeals(categoryName: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _meals.update {
                    MealsApiService.callable.getMeals(categoryName).meals
                }
                _hasError.update { false }
            } catch (e: Exception){
                Log.d("trace","Error: ${e.message}")
                _hasError.update { true }
            }
        }
    }

}