package com.example.dogs.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dog(
    val name: String,
    val url: String
): Parcelable
