package com.example.architecture.data.remote.api

import com.example.architecture.data.remote.model.Animal
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>
}