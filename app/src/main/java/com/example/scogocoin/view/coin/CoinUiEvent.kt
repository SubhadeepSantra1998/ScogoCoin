package com.example.scogocoin.view.coin


sealed class CoinUiEvent{

    data class SearchQueryChanged(val query: String) : CoinUiEvent()
    data object Search : CoinUiEvent()
}
