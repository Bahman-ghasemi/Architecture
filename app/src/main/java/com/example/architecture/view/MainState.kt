package com.example.architecture.view

import com.example.architecture.model.entity.Animal

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Success(val animals: List<Animal>) : MainState()
    data class Error(val message: String?) : MainState()
}