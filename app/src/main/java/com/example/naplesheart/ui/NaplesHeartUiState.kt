package com.example.naplesheart.ui

import com.example.naplesheart.data.RecommendationCategory
import com.example.naplesheart.data.LocalRecommendationDataProvider
import com.example.naplesheart.data.Recommendation

data class NaplesHeartUiState(
    val recommendations: Map<RecommendationCategory,List<Recommendation>> = emptyMap(),
    val currentRecommendationCategory: RecommendationCategory = RecommendationCategory.Pizza,
    val currentSelectedRecommendation: Recommendation = LocalRecommendationDataProvider.defaultRecommendation,
    val isShowingHomepage: Boolean = true
) {
    val currentCategoryRecommendation: List<Recommendation> by lazy { recommendations[currentRecommendationCategory]!! }
}