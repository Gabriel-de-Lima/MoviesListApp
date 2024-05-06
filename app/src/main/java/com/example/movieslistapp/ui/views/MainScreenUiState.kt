package com.example.movieslistapp.ui.views

import androidx.annotation.DrawableRes

data class MainScreenUiState(
    val screenName: String,
    @DrawableRes val icon: Int,
    val iconContentDescription: String,
)
