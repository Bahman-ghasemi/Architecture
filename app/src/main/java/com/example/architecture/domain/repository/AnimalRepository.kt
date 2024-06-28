package com.example.architecture.domain.repository

import com.example.architecture.data.remote.model.Animal

interface AnimalRepository {
    suspend fun fetchAnimals():List<Animal>
}