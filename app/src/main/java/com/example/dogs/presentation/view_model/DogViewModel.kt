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
class DogViewModel @Inject constructor(
    private val getListOfDogs: IGetDogsRepository
): ViewModel() {


    private val _aboutDog: MutableLiveData<DogAppState> = MutableLiveData()
    val aboutDog: LiveData<DogAppState>
        get(){
            return _aboutDog
        }

    fun setData(dog: Dog?) {
        if (dog != null){

            viewModelScope.launch {

                val response = getListOfDogs.getDogsImagesList(dog.name)
                if (response.isSuccessful){
                    _aboutDog.value = response.body()?.let {
                        DogAppState.OnSuccess(
                            dog.name,
                            it.message,
                            "Very Good dogy"
                        )
                    }
                }else{
                    _aboutDog.value = DogAppState.Error("Error to loading Data")
                }
            }


        }
    }
}