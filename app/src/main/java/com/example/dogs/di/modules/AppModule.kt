package com.example.dogs.di.modules

import com.example.dogs.api.IDogsAPI
import com.example.dogs.data.GetDogsRepositoryImpl
import com.example.dogs.domain.IGetDogsRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    @Named("baseURL")
    @Provides
    fun baseURL(): String = "https://dog.ceo/"


    @Provides
    @Singleton
    fun api(@Named("baseURL") baseURL: String, gson: Gson): IDogsAPI =
        Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDogsAPI::class.java)


    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun getDogs(api: IDogsAPI): IGetDogsRepository = GetDogsRepositoryImpl(api)
}