package com.example.scogocoin.data.remote.repository

import com.example.scogocoin.common.Resource
import com.example.scogocoin.data.model.CoinDetailsModel
import com.example.scogocoin.data.model.CoinModel
import com.example.scogocoin.data.remote.CoinPaprikaApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): Flow<Resource<List<CoinModel>>> = flow{
        emit(Resource.Loading())
        try {
            val result = api.getCoins()
            emit(Resource.Success(result))
        }catch (e: HttpException){
            emit(Resource.Error(e.message?: "Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error(e.message?: "Please enter your internet connection"))
        }catch (e: Exception){
            emit(Resource.Error(e.message?: "Something went wrong, try again"))
        }
    }

    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetailsModel>> = flow{
        emit(Resource.Loading())
        try {
            val result = api.getCoinById(coinId)
            emit(Resource.Success(result))
        }catch (e: HttpException){
            emit(Resource.Error(e.message?: "Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error(e.message?: "Please enter your internet connection"))
        }catch (e: Exception){
            emit(Resource.Error(e.message?: "Something went wrong"))
        }
    }


}