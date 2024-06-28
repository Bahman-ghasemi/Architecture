package com.example.architecture.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.model.repository.AnimalRepository
import com.example.architecture.view.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AnimalRepository
) : ViewModel() {

    var state by mutableStateOf<MainState>(MainState.Idle)
        private set

    fun fetchAnimals() {
        viewModelScope.launch {
            state = MainState.Loading
            state = try {
                MainState.Success(repo.getAnimals())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

}