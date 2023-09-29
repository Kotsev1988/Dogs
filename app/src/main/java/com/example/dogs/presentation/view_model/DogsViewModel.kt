package com.example.dogs.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.domain.IGetDogsRepository
import com.example.dogs.domain.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val getListOfDogs: IGetDogsRepository,
) : ViewModel() {

    private val _dogs: MutableLiveData<DogsAppState> = MutableLiveData()
    val hotelItem: LiveData<DogsAppState>
        get(){
            return _dogs
        }
    private val listOfDogs: MutableList<Dog> = mutableListOf()

    fun getListOfDogs(){
        viewModelScope.launch {
          val response = getListOfDogs.getListOfDogs()

            if (response.isSuccessful){

                response.body()?.message?.forEach {dogName ->
                  val pictureOfDog =  getListOfDogs.getDogImages(dogName)

                    if (pictureOfDog.isSuccessful){

                        val url = pictureOfDog.body()?.message
                        if (url != null){
                            listOfDogs.add(Dog(
                                dogName,
                                url
                            ))
                        }
                    }
                }

                _dogs.value = response.body()?.let {
                    DogsAppState.OnSuccess(listOfDogs)
                }

            }else{
                _dogs.value = DogsAppState.Error("Error to loading Data")
            }
        }
    }
}