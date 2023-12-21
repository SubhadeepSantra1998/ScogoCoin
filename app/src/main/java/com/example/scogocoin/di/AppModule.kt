package com.example.scogocoin.di

import com.example.scogocoin.common.Constants
import com.example.scogocoin.data.remote.CoinPaprikaApi
import com.example.scogocoin.data.remote.repository.CoinRepository
import com.example.scogocoin.data.remote.repository.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesCoinPaprikaApi(): CoinPaprikaApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCoinRepository(api: CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImpl(api = api)
    }

}