package com.example.dogs.presentation

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.dogs.R
import com.example.dogs.databinding.FragmentDogBinding
import com.example.dogs.databinding.FragmentDogsBinding
import com.example.dogs.domain.model.Dog
import com.example.dogs.presentation.adapter.DogsAdapter
import com.example.dogs.presentation.adapter.SliderAdapter
import com.example.dogs.presentation.view_model.DogAppState
import com.example.dogs.presentation.view_model.DogViewModel
import com.example.dogs.presentation.view_model.DogsAppState
import com.example.dogs.presentation.view_model.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogFragment : Fragment() {
    private var _binding: FragmentDogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentDogBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dog = arguments?.let {
            it.getParcelable("dogName", Dog::class.java)
        }

        println("Dog "+dog.toString())

        viewModel.aboutDog.observe(viewLifecycleOwner){
            renderData(it)
        }
        viewModel.setData(dog)


    }

    private fun renderData(it: DogAppState) {
        when (it) {
            is DogAppState.OnSuccess -> {

               binding.dogNameTitle.text = it.dogName

                binding.sliderDogs.setSliderAdapter(SliderAdapter(it.url))

                binding.dogDescription.text = it.description
            }

            is DogAppState.Error -> {
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }


        }
    }


}