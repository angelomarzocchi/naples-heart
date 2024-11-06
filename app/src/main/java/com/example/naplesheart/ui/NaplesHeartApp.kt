package com.example.naplesheart.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.naplesheart.ui.utils.NaplesHeartContentType
import com.example.naplesheart.ui.utils.NaplesHeartNavigationType


@Composable
fun NaplesHeartApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {

    val viewModel: NaplesHeartViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val navigationType: NaplesHeartNavigationType
    val contentType: NaplesHeartContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NaplesHeartNavigationType.BOTTOM_NAVIGATION
            contentType = NaplesHeartContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = NaplesHeartNavigationType.NAVIGATION_RAIL
            contentType = NaplesHeartContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = NaplesHeartNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = NaplesHeartContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = NaplesHeartNavigationType.BOTTOM_NAVIGATION
            contentType = NaplesHeartContentType.LIST_ONLY
        }
    }

}