package com.example.movieslistapp.ui.views

import androidx.annotation.DrawableRes
import com.example.movieslistapp.R

data class InsertMovieUiState(
    @DrawableRes val pic: Int = R.drawable.other,
    val name: String = "",
    val description: String = "",
    val watched: Boolean = false,
    val rating: Int = 0
)
