package com.example.dogs.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.dogs.databinding.DogsSliderBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(imageUrl: List<String>): SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    private val sliderList: List<String> = imageUrl

    override fun getCount(): Int = sliderList.size

    override fun onCreateViewHolder(parent: ViewGroup) = SliderViewHolder(
        DogsSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.bind(sliderList[position])
    }

    inner class SliderViewHolder(val binding: DogsSliderBinding ) : ViewHolder(binding.root){
        fun bind(sliderList: String) {
            Glide.with(binding.dogImage.context).load(sliderList).fitCenter().into(binding.dogImage)
        }
    }
}