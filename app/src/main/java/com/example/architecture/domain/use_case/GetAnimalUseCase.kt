package com.example.architecture.domain.use_case

import com.example.architecture.data.remote.model.Animal
import com.example.architecture.domain.repository.AnimalRepository
import javax.inject.Inject

class GetAnimalUseCase @Inject constructor(
    private val repo: AnimalRepository
) {
    suspend fun invoke(): List<Animal> {
        return repo.fetchAnimals()
    }
}