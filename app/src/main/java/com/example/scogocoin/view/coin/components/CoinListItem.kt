package com.example.scogocoin.view.coin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.scogocoin.R
import com.example.scogocoin.common.components.BodyTextComponent
import com.example.scogocoin.common.components.TitleTextComponent
import com.example.scogocoin.data.model.CoinModel

@Composable
fun CoinListItem(
    coin: CoinModel,
    onItemClick: (CoinModel) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(coin)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TitleTextComponent(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            modifier = Modifier.weight(1f)
        )

        BodyTextComponent(
            text = if (coin.is_active) stringResource(id = R.string.active) else stringResource(id = R.string.inactive),
            textColor = if(coin.is_active) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
        )
    }
}