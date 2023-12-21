package com.example.scogocoin.view.coin_details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.scogocoin.R
import com.example.scogocoin.common.components.BodyTextComponent
import com.example.scogocoin.common.components.HeadingTextComponent
import com.example.scogocoin.common.components.TitleTextComponent
import com.example.scogocoin.ui.theme.ScogoCoinTheme
import com.example.scogocoin.view.coin.CoinScreenContent
import com.example.scogocoin.view.coin.CoinUiState
import com.example.scogocoin.view.coin_details.components.CoinTag
import com.example.scogocoin.view.coin_details.components.TeamListItem
import com.example.scogocoin.viewModel.CoinDetailViewModel

@Composable
fun CoinDetailsScreen(
    viewModel:CoinDetailViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    CoinDetailsScreenContent(
        uiState = state.value,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreenContent(
    uiState: CoinDetailUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {

        uiState.coin?.let { coin ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 24.dp)
            ) {

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                            AsyncImage(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(shape = RoundedCornerShape(80.dp)),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(coin.logo)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )

                        Spacer(modifier = Modifier.width(8.dp))
                            HeadingTextComponent(
                                text = "${coin.name} (${coin.symbol})",
                                textColor = if(coin.is_active) Color.Green else Color.Red,
                            )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    BodyTextComponent(text = coin.description)

                    Spacer(modifier = Modifier.height(24.dp))
                    TitleTextComponent(text = stringResource(id = R.string.tags))

                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag.name)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    TitleTextComponent(text = stringResource(id = R.string.team_members))

                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }

            uiState.apply {
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
}


@Preview(showBackground = true)
@Composable
fun CoinDetailsScreenPreview() {
    ScogoCoinTheme {

        CoinDetailsScreenContent(
            uiState = CoinDetailUiState()
        )
    }
}
