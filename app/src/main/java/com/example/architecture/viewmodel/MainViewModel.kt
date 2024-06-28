package com.example.architecture.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture.model.entity.Animal
import com.example.architecture.model.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AnimalRepository
) : ViewModel() {

    var isIdle = mutableStateOf(true)
        private set

    var isLoading = mutableStateOf(false)
        private set

    var animals = mutableStateOf(emptyList<Animal>())
        private set

    var hasError = mutableStateOf<String?>(null)
        private set

    private fun setLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    private fun setAnimals(animals: List<Animal>) {
        this.animals.value = animals
    }

    private fun setError(hasError: String?) {
        this.hasError.value = hasError
    }


    fun fetchAnimals() {
        viewModelScope.launch {
            isIdle.value = false
            isLoading.value = true
            try {
                setAnimals(repo.getAnimals())
                setError(null)

            } catch (e: Exception) {
                setError(e.localizedMessage)
                setAnimals(emptyList())
            }
            isLoading.value = false
        }
    }

}