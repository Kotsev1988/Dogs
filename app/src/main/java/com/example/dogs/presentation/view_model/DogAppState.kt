package com.example.dogs.presentation.view_model

import com.example.dogs.domain.model.Dog

sealed class DogAppState {
    data class OnSuccess(
        val dogName: String,
        val url: List<String>,
        val description: String,
    ) : DogAppState()

    data class Error(val error: String) : DogAppState()

}
