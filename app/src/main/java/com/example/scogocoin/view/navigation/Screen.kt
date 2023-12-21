package com.example.scogocoin.view.navigation

import com.example.scogocoin.common.utils.Route


sealed class Screen(val route: String) {
    data object CoinScreen : Screen(route = Route.COIN.value)
    data object CoinDetailsScreen : Screen(route = Route.COIN_DETAILS.value)
}