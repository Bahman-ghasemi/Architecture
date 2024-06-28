package com.example.architecture.model.repository

import com.example.architecture.model.entity.Animal
import com.example.architecture.model.remote.AnimalApi
import javax.inject.Inject

class AnimalRepository @Inject constructor(private val api: AnimalApi) {
    suspend fun getAnimals(): List<Animal> {
        return  api.getAnimals()
    }
}