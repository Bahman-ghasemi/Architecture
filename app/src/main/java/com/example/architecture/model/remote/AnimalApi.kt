package com.example.architecture.model.remote

import com.example.architecture.model.entity.Animal
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>
}