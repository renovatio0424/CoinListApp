package com.reno.coinlistapp.di

import com.reno.coinlistapp.BuildConfig
import com.reno.coinlistapp.data.CoinApi
import com.reno.coinlistapp.data.CoinRepository
import com.reno.coinlistapp.data.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCoinApi(): CoinApi = Retrofit.Builder()
        .baseUrl(BuildConfig.COIN_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoinApi::class.java)

    @Singleton
    @Provides
    fun provideCoinRepository(coinApi: CoinApi): CoinRepository = CoinRepositoryImpl(coinApi)
}