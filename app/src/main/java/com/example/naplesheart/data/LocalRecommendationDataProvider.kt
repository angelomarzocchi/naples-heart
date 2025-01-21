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
         title = R.string.recommendation_title_2,
         description = R.string.recommendation_description_2,
         location = R.string.recommendation_location_2,
         image = R.drawable.pizza_02,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 3L,
         title = R.string.recommendation_title_3,
         description = R.string.recommendation_description_3,
         location = R.string.recommendation_location_3,
         image = R.drawable.pizza_03,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 4L,
         title = R.string.recommendation_title_4,
         description = R.string.recommendation_description_4,
         location = R.string.recommendation_location_4,
         image = R.drawable.pizza_04,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 5L,
         title = R.string.recommendation_title_5,
         description = R.string.recommendation_description_5,
         location = R.string.recommendation_location_5,
         image = R.drawable.pizza_05,
         recommendationCategory = RecommendationCategory.Pizza
      ),
      Recommendation(
         id = 6L,
         title = R.string.recommendation_title_6,
         description = R.string.recommendation_description_6,
         location = R.string.recommendation_location_1,
         image = R.drawable.aperitivo_01,
         recommendationCategory = RecommendationCategory.Aperitif
      ),
      Recommendation(
         id = 7L,
         title = R.string.recommendation_title_7,
         description = R.string.recommendation_description_7,
         location = R.string.recommendation_location_7,
         image = R.drawable.aperitivo_03,
         recommendationCategory = RecommendationCategory.Aperitif
      ),
      Recommendation(
         id = 8L,
         title = R.string.recommendation_title_8,
         description = R.string.recommendation_description_8,
         location = R.string.recommendation_location_8,
         image = R.drawable.aperitivo_03,
         recommendationCategory = RecommendationCategory.Aperitif
      ),
      Recommendation(
         id = 9L,
         title = R.string.recommendation_title_9,
         description = R.string.recommendation_description_9,
         location = R.string.recommendation_location_9,
         image = R.drawable.aperitivo_04,
         recommendationCategory = RecommendationCategory.Aperitif
      ),
      Recommendation(
         id = 10L,
         title = R.string.recommendation_title_10,
         description = R.string.recommendation_description_10,
         location = R.string.recommendation_location_10,
         image = R.drawable.aperitivo_05,
         recommendationCategory = RecommendationCategory.Aperitif
      ),
      Recommendation(
         id = 11L,
         title = R.string.recommendation_title_11,
         description = R.string.recommendation_description_11,
         location = R.string.recommendation_location_11,
         image = R.drawable.murales_diego,
         recommendationCategory = RecommendationCategory.Monuments
      ),
      Recommendation(
         id = 12L,
         title = R.string.recommendation_title_12,
         description = R.string.recommendation_description_12,
         location = R.string.recommendation_location_12,
         image = R.drawable.obelisco,
         recommendationCategory = RecommendationCategory.Monuments
      ),
      Recommendation(
         id = 13L,
         title = R.string.recommendation_title_13,
         description = R.string.recommendation_description_13,
         location = R.string.recommendation_location_13,
         image = R.drawable.castel_nuovo,
         recommendationCategory = RecommendationCategory.Monuments
      ),
      Recommendation(
         id = 14L,
         title = R.string.recommendation_title_14,
         description = R.string.recommendation_description_14,
         location = R.string.recommendation_location_14,
         image = R.drawable.plebisccito,
         recommendationCategory = RecommendationCategory.Monuments
      ),
      Recommendation(
         id = 15L,
         title = R.string.recommendation_title_15,
         description = R.string.recommendation_description_15,
         location = R.string.recommendation_location_15,
         image = R.drawable.spaccanapoli,
         recommendationCategory = RecommendationCategory.Monuments
      )
   )

   val defaultRecommendation: Recommendation = allRecommendation[0]
}