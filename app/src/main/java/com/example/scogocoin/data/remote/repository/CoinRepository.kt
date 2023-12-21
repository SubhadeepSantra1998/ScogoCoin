package com.example.scogocoin.data.remote.repository

import com.example.scogocoin.common.Resource
import com.example.scogocoin.data.model.CoinDetailsModel
import com.example.scogocoin.data.model.CoinModel
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<Resource<List<CoinModel>>>

    suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetailsModel>>

}