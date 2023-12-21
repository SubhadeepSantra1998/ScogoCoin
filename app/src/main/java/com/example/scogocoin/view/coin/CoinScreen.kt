package com.example.scogocoin.view.coin

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.scogocoin.R
import com.example.scogocoin.common.components.PullRefreshComponent
import com.example.scogocoin.common.components.SearchBarComponent
import com.example.scogocoin.ui.theme.ScogoCoinTheme
import com.example.scogocoin.view.coin.components.CoinListItem
import com.example.scogocoin.viewModel.CoinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinsScreen(
    onCoinDetailsScreen: (String) -> Unit,
    viewModel: CoinViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    CoinScreenContent(

        uiState = state.value,
        onEvent = {
            viewModel.onEvent(it)
        },
        onCoinDetailsScreen = onCoinDetailsScreen,
        pullRefreshState = rememberPullRefreshState(state.value.isRefreshing, { viewModel.getAllCoins() })
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinScreenContent(
    onCoinDetailsScreen: (String) -> Unit,
    uiState: CoinUiState,
    onEvent: (CoinUiEvent) -> Unit,
    pullRefreshState: PullRefreshState,
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .padding(horizontal = 12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchBarComponent(
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = {
                    onEvent(CoinUiEvent.SearchQueryChanged(it))
                    onEvent(CoinUiEvent.Search)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                placeholder = stringResource(id = R.string.search_coin)
            ) {}

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {

                items(uiState.coins!!){ data->
                    CoinListItem(
                        coin = data,
                        onItemClick = {
                            onCoinDetailsScreen(data.id)
                        }
                    )
                }
            }
        }



        uiState.apply {


            PullRefreshComponent(
                isRefreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            errorMessage?.let {message->
                Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun CoinsScreenPreview() {
    ScogoCoinTheme {

        CoinScreenContent(
            onCoinDetailsScreen = {},
            uiState = CoinUiState(),
            onEvent = {},
            pullRefreshState = rememberPullRefreshState(refreshing = false, onRefresh = { /*TODO*/ })
        )
    }
}
