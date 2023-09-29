package com.example.dogs.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dogs.R
import com.example.dogs.databinding.FragmentDogsBinding
import com.example.dogs.domain.model.Dog
import com.example.dogs.presentation.adapter.DogsAdapter
import com.example.dogs.presentation.view_model.DogsAppState
import com.example.dogs.presentation.view_model.DogsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogsFragment : Fragment() {

    private var _binding: FragmentDogsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DogsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDogsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.hotelItem.observe(viewLifecycleOwner) {
            renderData(it)
        }
        binding.frameLoadDishes.visibility = View.VISIBLE
        viewModel.getListOfDogs()


    }

    private fun renderData(it: DogsAppState) {
        when (it) {
            is DogsAppState.OnSuccess -> {

                binding.dogsRecycler.adapter = DogsAdapter(
                    object : OnItemClick {

                        override fun onClick(dog: Dog) {
                            val bundle = Bundle()
                            bundle.putParcelable("dogName", dog)

                            findNavController().navigate(R.id.action_dogsFragment_to_dogFragment, bundle)
                        }
                    },
                    it.dogs
                )
                binding.frameLoadDishes.visibility = View.GONE
            }

            is DogsAppState.Error -> {
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }

            is DogsAppState.Loading -> {

            }
        }
    }
}