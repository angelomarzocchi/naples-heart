package com.example.naplesheart.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.naplesheart.NaplesHeartTheme
import com.example.naplesheart.R
import com.example.naplesheart.data.Recommendation
import com.example.naplesheart.data.RecommendationCategory
import com.example.naplesheart.ui.utils.NaplesHeartContentType
import com.example.naplesheart.ui.utils.NaplesHeartNavigationType

@Composable
fun NaplesHeartHomeScreen(
    navigationType: NaplesHeartNavigationType,
    contentType: NaplesHeartContentType,
    uiState: NaplesHeartUiState,
    onTabPressed: (RecommendationCategory) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            recommendationCategory = RecommendationCategory.Pizza,
            icon = painterResource(R.drawable.pizza_icon),
            selectedIcon = painterResource(R.drawable.pizza_icon_selected),
            text = stringResource(R.string.pizza_label)
        ),
        NavigationItemContent(
            recommendationCategory = RecommendationCategory.Monuments,
            icon = painterResource(R.drawable.monument_icon),
            selectedIcon = painterResource(R.drawable.monument_icon_selected),
            text = stringResource(R.string.monument_label)
        ),
        NavigationItemContent(
            recommendationCategory = RecommendationCategory.Aperitif,
            icon = painterResource(id = R.drawable.aperitif_icon),
            selectedIcon = painterResource(R.drawable.aperitif_icon_selected),
            text = stringResource(R.string.aperitif_label)
        )
    )

    if(navigationType == NaplesHeartNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    Modifier.width(dimensionResource(id = R.dimen.drawewr_width)),
                    drawerContainerColor = MaterialTheme.colorScheme.inverseSurface
                ) {
                    NavigationDrawerContent(
                        currentRecommendationCategory = uiState.currentRecommendationCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseSurface)
                            .padding(dimensionResource(id = R.dimen.drawer_padding_content))
                    )
                }
            }
        ) {
            NaplesHeartAppContent(
                navigationType = navigationType,
                contentType = contentType,
                uiState = uiState,
                onTabPressed = onTabPressed,
                onRecommendationPressed = onRecommendationPressed,
                onDetailScreenBackPressed = onDetailScreenBackPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        }
    } else {
        if(uiState.isShowingHomepage) {
            NaplesHeartAppContent(
                navigationType = navigationType,
                contentType = contentType,
                uiState = uiState,
                onTabPressed = onTabPressed,
                onRecommendationPressed = onRecommendationPressed,
                onDetailScreenBackPressed = onDetailScreenBackPressed,
                navigationItemContentList = navigationItemContentList,
                modifier = modifier
            )
        } else {
            NaplesHeartDetailsScreen(
                uiState = uiState,
                onBackPressed = { onDetailScreenBackPressed() },
                isFullScreen = true,
                modifier = modifier
            )
        }

    }


}

@Composable
private fun NaplesHeartAppContent(
    navigationType: NaplesHeartNavigationType,
    contentType: NaplesHeartContentType,
    uiState: NaplesHeartUiState,
    onTabPressed: (RecommendationCategory) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == NaplesHeartNavigationType.NAVIGATION_RAIL) {
                NaplesHeartNavigationRail(
                    currentRecommendationCategory = uiState.currentRecommendationCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                if (contentType == NaplesHeartContentType.LIST_AND_DETAIL) {
                    NaplesHeartListAndDetailContent(
                        uiState = uiState,
                        onRecommendationPressed = onRecommendationPressed,
                        modifier = Modifier
                            .statusBarsPadding()
                            .weight(1f)
                    )
                } else {
                    NaplesHeartListOnlyContent(
                        uiState = uiState,
                        onCardClick = onRecommendationPressed,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = dimensionResource(R.dimen.recommendation_list_only_horizontal_padding))
                    )
                }
                AnimatedVisibility(
                    visible = navigationType == NaplesHeartNavigationType.BOTTOM_NAVIGATION
                ) {
                    NaplesHeartBottomNavigationBar(
                        currentRecommendationCategory = uiState.currentRecommendationCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

}




@Composable
private fun NaplesHeartNavigationRail(
    currentRecommendationCategory: RecommendationCategory,
    onTabPressed: (RecommendationCategory) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for(navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentRecommendationCategory == navItem.recommendationCategory,
                onClick = { onTabPressed(navItem.recommendationCategory) },
                icon = {
                    Icon(
                        painter =if(currentRecommendationCategory == navItem.recommendationCategory) navItem.selectedIcon else navItem.icon,
                        contentDescription = navItem.text,
                        tint = if(currentRecommendationCategory == navItem.recommendationCategory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,

                    )
                }

            )
        }
    }
}

@Composable
private fun NaplesHeartBottomNavigationBar(
    currentRecommendationCategory: RecommendationCategory,
    onTabPressed: (RecommendationCategory) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for(navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentRecommendationCategory == navItem.recommendationCategory,
                onClick = { onTabPressed(navItem.recommendationCategory) },
                icon = {
                    Icon(
                        painter =if(currentRecommendationCategory == navItem.recommendationCategory) navItem.selectedIcon else navItem.icon,
                        contentDescription = navItem.text,
                        tint = if(currentRecommendationCategory == navItem.recommendationCategory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,

                        )
                }
            )

        }
    }
}

@Composable
private fun NavigationDrawerContent(
    currentRecommendationCategory: RecommendationCategory,
    onTabPressed: (RecommendationCategory) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        NavigationDrawerHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.header_padding_size))
        )
        for(navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = currentRecommendationCategory == navItem.recommendationCategory,
                onClick = { onTabPressed(navItem.recommendationCategory) },
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header))
                    )
                },
                icon = {
                    Icon(
                        painter =if(currentRecommendationCategory == navItem.recommendationCategory) navItem.selectedIcon else navItem.icon,
                        contentDescription = navItem.text,
                        tint = if(currentRecommendationCategory == navItem.recommendationCategory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NaplesHeartLogo(modifier = Modifier.size(dimensionResource(R.dimen.naples_heart_size)))
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
fun NaplesHeartHomeScreenPreview() {
    NaplesHeartTheme {
        NaplesHeartHomeScreen(
            navigationType = NaplesHeartNavigationType.PERMANENT_NAVIGATION_DRAWER,
            contentType = NaplesHeartContentType.LIST_AND_DETAIL,
            uiState = NaplesHeartUiState(),
            onTabPressed = {},
            onRecommendationPressed = {},
            onDetailScreenBackPressed = {}
        )
    }
}

@Preview
@Composable
fun NavigationDrawerHeaderPreview() {
    NaplesHeartTheme {
        NavigationDrawerHeader()
    }
}




@Preview
@Composable
fun NaplesHeartBottomNavigationBarPreview() {
    NaplesHeartTheme {
        NaplesHeartBottomNavigationBar(
            currentRecommendationCategory = RecommendationCategory.Aperitif,
            onTabPressed = {},
            navigationItemContentList = listOf(
                NavigationItemContent(
                    recommendationCategory = RecommendationCategory.Pizza,
                    icon = painterResource(R.drawable.pizza_icon),
                    selectedIcon = painterResource(R.drawable.pizza_icon_selected),
                    text = stringResource(R.string.pizza_label)
                ),
                NavigationItemContent(
                    recommendationCategory = RecommendationCategory.Monuments,
                    icon = painterResource(R.drawable.monument_icon),
                    selectedIcon = painterResource(R.drawable.monument_icon_selected),
                    text = stringResource(R.string.monument_label)
                ),
                NavigationItemContent(
                    recommendationCategory = RecommendationCategory.Aperitif,
                    icon = painterResource(id = R.drawable.aperitif_icon),
                    selectedIcon = painterResource(R.drawable.aperitif_icon_selected),
                    text = stringResource(R.string.aperitif_label)
                )
            )
        )
    }
}


@Preview
@Composable
fun NaplesHeartNavigationRailPreview() {
    NaplesHeartTheme {
        NaplesHeartNavigationRail(
            currentRecommendationCategory = RecommendationCategory.Aperitif,
            onTabPressed = {},
            navigationItemContentList = listOf(
                NavigationItemContent(
                    recommendationCategory = RecommendationCategory.Pizza,
                    icon = painterResource(R.drawable.pizza_icon),
                    selectedIcon = painterResource(R.drawable.pizza_icon_selected),
                    text = stringResource(R.string.pizza_label)
                ),
                NavigationItemContent(
                    recommendationCategory = RecommendationCategory.Monuments,
                    icon = painterResource(R.drawable.monument_icon),
                    selectedIcon = painterResource(R.drawable.monument_icon_selected),
                    text = stringResource(R.string.monument_label)
                ),
                NavigationItemContent(
                    recommendationCategory = RecommendationCategory.Aperitif,
                    icon = painterResource(id = R.drawable.aperitif_icon),
                    selectedIcon = painterResource(R.drawable.aperitif_icon_selected),
                    text = stringResource(R.string.aperitif_label)
                )
            )
        )
    }
}

private data class NavigationItemContent(
    val recommendationCategory: RecommendationCategory,
    val icon: Painter,
    val selectedIcon: Painter,
    val text: String
)