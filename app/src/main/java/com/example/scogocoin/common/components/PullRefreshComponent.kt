package com.example.scogocoin.common.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshComponent(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    state: PullRefreshState
) {
    PullRefreshIndicator(
        modifier = modifier,
        refreshing = isRefreshing,
        state = state

    )
}