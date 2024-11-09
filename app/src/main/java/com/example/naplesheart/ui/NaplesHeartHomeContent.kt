package com.example.naplesheart.ui


import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.naplesheart.NaplesHeartTheme
import com.example.naplesheart.R
import com.example.naplesheart.data.LocalRecommendationDataProvider
import com.example.naplesheart.data.Recommendation


@Composable
fun NaplesHeartListOnlyContent(
    uiState: NaplesHeartUiState,
    onCardClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendations = uiState.currentCategoryRecommendation

    LazyColumn(
        modifier = modifier,
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_list_vertical_spacing))
    ) {
        item {
            NaplesHeartHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.topbar_padding_vertical))
            )
        }
        items(recommendations, key = { recommendation -> recommendation.id}) { recommendation ->
            NaplesHeartListItem(
                recommendation = recommendation,
                selected = recommendation.id == uiState.currentSelectedRecommendation.id,
                onCardClick = {
                    onCardClick(recommendation)
                },
            )
        }
    }
}

@Composable
fun NaplesHeartListAndDetailContent(
    uiState: NaplesHeartUiState,
    onRecommendationPressed: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    val recommendations = uiState.currentCategoryRecommendation
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.recommendation_list_only_horizontal_padding)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.recommendation_list_item_vertical_spacing))
            ) {
            items(recommendations, key = { recommendation -> recommendation.id}) { recommendation ->
                NaplesHeartListItem(
                    recommendation = recommendation,
                    selected =  uiState.currentSelectedRecommendation.id == recommendation.id,
                    onCardClick = {
                        onRecommendationPressed(recommendation)
                    }
                )
            }
    }
        val activity = LocalContext.current as Activity
        NaplesHeartDetailsScreen(
            uiState = uiState,
            onBackPressed = {activity.finish()},
            isFullScreen = false,
            modifier = Modifier.weight(1f),
        )
    }
}


@Composable
fun NaplesHeartListItem(
    recommendation: Recommendation,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = onCardClick
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.list_item_inner_padding))
            ) {
                //Header
                Text(
                    text = stringResource( recommendation.title),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(4.dp)
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(4.dp))
                Text(
                    text = stringResource(recommendation.description),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                )

            }
        }

    }
}

@Composable
fun NaplesHeartLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    ) {
    Image(
        painter = painterResource(R.drawable.naples_heart_logo),
        contentDescription = stringResource(R.string.naples_heart_logo),
        modifier = modifier,
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
private fun NaplesHeartHomeTopBar(modifier: Modifier = Modifier) {
   Row(
       horizontalArrangement = Arrangement.SpaceBetween,
       verticalAlignment = Alignment.CenterVertically,
       modifier = modifier
   ) {
       NaplesHeartLogo(
           modifier = Modifier
               .size(dimensionResource(R.dimen.topbar_logo_size))
               .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
       )
       Text(
           text = stringResource(R.string.app_name),
           style = MaterialTheme.typography.titleLarge,
           color = MaterialTheme.colorScheme.onSurface
       )
   }
}

@Preview
@Composable
fun NaplesHeartHomeTopBarPreview() {
    NaplesHeartTheme {
        Surface {
            NaplesHeartHomeTopBar()
        }

    }
}

@Preview
@Composable
fun NaplesHeartListItemPreview() {
    NaplesHeartTheme {
        Surface {
            NaplesHeartListItem(
                LocalRecommendationDataProvider.defaultRecommendation,
                selected = false,
                onCardClick = {}
            )
        }
    }
}