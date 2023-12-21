package com.example.scogocoin.view.coin_details

import com.example.scogocoin.data.model.CoinDetailsModel
import com.example.scogocoin.data.model.CoinModel

data class CoinDetailUiState(
    val isLoading:Boolean = false,
    val isRefreshing: Boolean = false,
    val coin: CoinDetailsModel? = null,
    val errorMessage: String? = null
)