package com.example.dogs.data

import com.example.dogs.api.IDogsAPI
import com.example.dogs.domain.IGetDogsRepository
import com.example.dogs.domain.model.Dogs
import com.example.dogs.domain.model.ImageOfDog
import com.example.dogs.domain.model.listOfImagesDog
import retrofit2.Response

class GetDogsRepositoryImpl(private val api: IDogsAPI) : IGetDogsRepository {

    override suspend fun getListOfDogs(): Response<Dogs> = api.getListOfDogs()
    override suspend fun getDogImages(dog: String): Response<ImageOfDog> = api.getDogImages(dog)
    override suspend fun getDogsImagesList(dog: String): Response<listOfImagesDog> = api.getDogListImages(dog)

}