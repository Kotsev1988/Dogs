package com.example.dogs.presentation

import com.example.dogs.domain.model.Dog

interface OnItemClick {
    fun onClick(dog: Dog)
}