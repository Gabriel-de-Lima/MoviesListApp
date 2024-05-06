package com.example.movieslistapp.models

import androidx.annotation.DrawableRes

data class Movie(
    @DrawableRes val pic: Int,
    val name: String,
    val description: String,
    val watched: Boolean,
    val rating: Int
)
