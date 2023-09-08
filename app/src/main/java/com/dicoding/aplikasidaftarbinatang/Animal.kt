package com.dicoding.aplikasidaftarbinatang

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable

