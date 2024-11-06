package com.example.naplesheart.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    val id: Long,
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
    val recommendationCategory: RecommendationCategory,
    @StringRes val location: Int
)
