package com.example.dogs.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogs.databinding.ItemDogBinding
import com.example.dogs.domain.model.Dog
import com.example.dogs.presentation.OnItemClick


class DogsAdapter(
    private var onItemClickListener: OnItemClick,
    private val listOfDogs: List<Dog>,
) : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder =
        DogsViewHolder(
            ItemDogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listOfDogs.size

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.bind(listOfDogs[position])
    }

    inner class DogsViewHolder(private val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {

            binding.dogName.text = dog.name
            Glide.with(itemView.context).load(dog.url).centerCrop()
                .into(binding.dogImage)

            binding.dogImage.setOnClickListener {
                onItemClickListener.onClick(
                    dog
                )
            }

        }

    }
}