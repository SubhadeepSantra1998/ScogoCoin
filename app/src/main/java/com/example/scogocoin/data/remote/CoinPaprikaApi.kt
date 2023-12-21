package com.example.scogocoin.data.remote

import com.example.scogocoin.data.model.CoinDetailsModel
import com.example.scogocoin.data.model.CoinModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinModel>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsModel
}