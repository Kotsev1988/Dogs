package com.example.dogs.domain

import com.example.dogs.domain.model.Dogs
import com.example.dogs.domain.model.ImageOfDog
import com.example.dogs.domain.model.listOfImagesDog
import retrofit2.Response

interface IGetDogsRepository {

    suspend fun getListOfDogs(): Response<Dogs>

    suspend fun getDogImages(dog: String): Response<ImageOfDog>

    suspend fun getDogsImagesList(dog: String): Response<listOfImagesDog>
}