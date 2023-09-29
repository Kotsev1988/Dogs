package com.example.dogs.presentation.view_model

import com.example.dogs.domain.model.Dog
import com.example.dogs.domain.model.Dogs

sealed class DogsAppState{
    data class OnSuccess(val dogs: List<Dog>): DogsAppState()

    data class Error(val error: String) : DogsAppState()
    object Loading : DogsAppState()
}
