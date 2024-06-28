package com.example.architecture.model.entity

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Animal(
    val id : String = UUID.randomUUID().toString(),
    val name: String,
    val location: String,
    val lifespan: String,
    val image: String
)
