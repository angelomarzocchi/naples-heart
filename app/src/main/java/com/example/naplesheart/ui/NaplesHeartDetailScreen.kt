package com.example.naplesheart.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.naplesheart.NaplesHeartTheme
import com.example.naplesheart.R
import com.example.naplesheart.data.LocalRecommendationDataProvider
import com.example.naplesheart.data.Recommendation
import com.example.naplesheart.data.RecommendationCategory


@Composable
fun NaplesHeartDetailsScreen(
    uiState: NaplesHeartUiState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()
            ),
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseSurface)
        ) {
            item {
                if(isFullScreen) {
                    RecommendationDetailsScreenTopBar(
                        onBackButtonClicked = onBackPressed,
                        uiState = uiState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = dimensionResource(R.dimen.detail_topbar_padding_bottom),
                                top = dimensionResource(R.dimen.topbar_padding_vertical)
                            )
                    )
                }
                RecommendationDetailsCard(
                    recommendation = uiState.currentSelectedRecommendation,
                    modifier = if(isFullScreen) {
                        Modifier.padding(horizontal = dimensionResource(R.dimen.detail_card_outer_padding))
                    } else {
                        Modifier
                    }
                )
            }
        }
    }
}

@Composable
private fun RecommendationDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    uiState: NaplesHeartUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dimensionResource(R.dimen.detail_subject_padding_end))
        ) {
            Text(
                text = uiState.currentRecommendationCategory.name,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
private fun RecommendationDetailsCard(
    recommendation: Recommendation,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.detail_card_inner_padding))
        ) {

            DetailsScreenHeader(
                recommendation = recommendation,
                Modifier.fillMaxWidth()
            )
            if(isFullScreen) {
                Spacer(
                    modifier = Modifier
                        .height(
                        dimensionResource(
                            R.dimen.detail_content_padding_top
                        )
                    ))
            } else {
                HorizontalDivider(modifier = Modifier.padding(top = dimensionResource(R.dimen.detail_card_divider_padding)))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {

                    Icon(Icons.Default.LocationOn, tint = MaterialTheme.colorScheme.onSurface, contentDescription = null)
                    Text(
                        text = stringResource(recommendation.location),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(R.dimen.detail_content_padding_top),
                                bottom = dimensionResource(R.dimen.detail_expanded_subject_body_spacing)
                            )
                    )

                }
                HorizontalDivider(modifier = Modifier.padding(bottom = dimensionResource(R.dimen.detail_card_divider_padding)))
               
            }

            Text(
                text = stringResource(recommendation.description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.detail_expanded_subject_body_spacing)))
            Image(
                painter = painterResource( recommendation.image),
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxSize()

            )
        }
    }
}


@Composable
private fun DetailsScreenButtonBar(
    recommendationCategory: RecommendationCategory,
    displayToast: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
                ActionButton(
                    text = stringResource(id = R.string.reserve),
                    onButtonClicked = displayToast
                )
    }
}


@Composable
private fun DetailsScreenHeader(recommendation: Recommendation, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(R.dimen.recommendation_header_content_padding_horizontal),
                vertical = dimensionResource(R.dimen.recommendation_header_content_padding_vertical)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(recommendation.title),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
private fun ActionButton(
    text: String,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    containIrreversabileAction: Boolean = false
) {
    Box(modifier = modifier) {
        Button(
            onClick = { onButtonClicked(text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.detail_action_button_padding_vertical)),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                    if(containIrreversabileAction) {
                        MaterialTheme.colorScheme.onErrorContainer
                    } else {
                        MaterialTheme.colorScheme.primaryContainer
                    }
            )
        ) {
            Text(
                text = text,
                color = if(containIrreversabileAction) {
                    MaterialTheme.colorScheme.onError
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }

}


@Preview
@Composable
fun NaplesHeartDetailsScreenPreview() {
    NaplesHeartTheme {
        NaplesHeartDetailsScreen(
            uiState = NaplesHeartUiState(),
            onBackPressed = {},
            isFullScreen = true
        )
    }
}

@Preview
@Composable
fun RecommendationDetailTopBarPreview() {
    NaplesHeartTheme {
        Surface {
            RecommendationDetailsScreenTopBar(
                {},
                NaplesHeartUiState(),

            )
        }
    }
}


@Preview
@Composable
fun RecommendationDetailsCardPreview() {
    NaplesHeartTheme {
        Surface {
            RecommendationDetailsCard(
                recommendation = LocalRecommendationDataProvider.defaultRecommendation
            )
        }
    }
}


@Preview
@Composable
fun ActionButtonPreview() {
    NaplesHeartTheme {
        Surface {
            ActionButton("Ciao", {},  containIrreversabileAction = false)
        }
    }
}