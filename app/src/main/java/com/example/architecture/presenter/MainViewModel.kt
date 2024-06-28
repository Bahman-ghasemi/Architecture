package com.example.architecture.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.domain.use_case.GetAnimalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetAnimalUseCase
) : ViewModel() {

    var state by mutableStateOf<MainState>(MainState.Idle)
        private set

    fun onAction(action: MainIntent) {
        when (action) {
            MainIntent.FetchAnimals -> {
                viewModelScope.launch {
                    state = MainState.Loading
                    state = try {
                        MainState.Success(useCase.invoke())
                    } catch (e: Exception) {
                        MainState.Error(e.localizedMessage)
                    }
                }
            }
        }
    }

}