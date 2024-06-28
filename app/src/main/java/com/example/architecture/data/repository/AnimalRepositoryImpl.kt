package com.example.architecture.data.repository

import com.example.architecture.data.remote.api.AnimalApi
import com.example.architecture.data.remote.model.Animal
import com.example.architecture.domain.repository.AnimalRepository
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val api: AnimalApi
) : AnimalRepository {
    override suspend fun fetchAnimals(): List<Animal> {
        return api.getAnimals()
    }
}