package com.example.naplesheart.data

import com.example.naplesheart.R

object LocalRecommendationDataProvider {


   val allRecommendation: List<Recommendation> = listOf(
      Recommendation(
         id = 1L,
         title = R.string.recommendation_title_1,
         description = R.string.recommendation_description_1,
         location = R.string.recommendation_location_1,
         image = R.drawable.pizza_01,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 2L,
         title = R.string.recommendation_title_1,
         description = R.string.recommendation_description_1,
         location = R.string.recommendation_location_1,
         image = R.drawable.pizza_02,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 3L,
         title = R.string.recommendation_title_1,
         description = R.string.recommendation_description_1,
         location = R.string.recommendation_location_1,
         image = R.drawable.pizza_03,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 4L,
         title = R.string.recommendation_title_1,
         description = R.string.recommendation_description_1,
         location = R.string.recommendation_location_1,
         image = R.drawable.pizza_04,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 5L,
         title = R.string.recommendation_title_1,
         description = R.string.recommendation_description_1,
         location = R.string.recommendation_location_1,
         image = R.drawable.pizza_05,
         recommendationCategory = RecommendationCategory.Pizza
      )
   )

   val defaultRecommendation: Recommendation = allRecommendation[0]
}