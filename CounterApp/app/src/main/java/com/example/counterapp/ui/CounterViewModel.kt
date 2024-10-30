package com.example.counterapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel: ViewModel() {
    private var _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    fun increment(){
        _counter.update { it+1 }
    }
}