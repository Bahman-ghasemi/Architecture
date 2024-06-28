package com.example.architecture.presenter

import com.example.architecture.data.remote.model.Animal

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Success(val animals: List<Animal>) : MainState()
    data class Error(val message: String?) : MainState()
}