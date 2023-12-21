package com.example.scogocoin.view.coin

import com.example.scogocoin.data.model.CoinDetailsModel
import com.example.scogocoin.data.model.CoinModel

data class CoinUiState(
    val isLoading:Boolean = false,
    val isRefreshing: Boolean = false,
    val coins: List<CoinModel>? = emptyList(),
    val tempCoinList: List<CoinModel>? = emptyList(),
    var searchQuery: String = "",
    val errorMessage: String? = null
)