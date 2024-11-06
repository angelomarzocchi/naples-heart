package com.example.naplesheart.ui

import androidx.lifecycle.ViewModel
import com.example.naplesheart.data.RecommendationCategory
import com.example.naplesheart.data.LocalRecommendationDataProvider
import com.example.naplesheart.data.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NaplesHeartViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(NaplesHeartUiState())
    val uiState = _uiState.asStateFlow()

    private fun initializeUiState() {
        val recommendations = LocalRecommendationDataProvider.allRecommendation.groupBy { it.recommendationCategory }
        _uiState.value =
            NaplesHeartUiState(
                recommendations = recommendations,
                currentSelectedRecommendation = recommendations[RecommendationCategory.Pizza]?.get(0)
                    ?: LocalRecommendationDataProvider.defaultRecommendation
            )
    }


    fun updateDetailsScreenStates(recommendation: Recommendation) {
        _uiState.update {
            it.copy(
                currentSelectedRecommendation = recommendation,
                isShowingHomepage = false
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedRecommendation = it.recommendations[it.currentRecommendationCategory]?.get(0)
                    ?: LocalRecommendationDataProvider.defaultRecommendation,
                isShowingHomepage = true
            )
        }
    }

    fun updateCurrentCategory(category: RecommendationCategory) {
        _uiState.update {
            it.copy(currentRecommendationCategory = category)
        }
    }
}