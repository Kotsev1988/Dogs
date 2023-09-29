package com.example.dogs.api

import com.example.dogs.domain.model.Dogs
import com.example.dogs.domain.model.ImageOfDog
import com.example.dogs.domain.model.listOfImagesDog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IDogsAPI {

    @GET("api/breed/hound/list")
    suspend fun getListOfDogs(): Response<Dogs>



    @GET("api/breed/hound/{dog}/images/random")
    suspend fun getDogImages(@Path("dog") dog: String): Response<ImageOfDog>

    @GET("api/breed/hound/{dog}/images")
    suspend fun getDogListImages(@Path("dog") dog: String): Response<listOfImagesDog>


}