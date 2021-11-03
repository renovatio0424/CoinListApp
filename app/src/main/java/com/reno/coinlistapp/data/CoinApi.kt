package com.reno.coinlistapp.data

import com.reno.coinlistapp.data.model.Coin
import com.reno.coinlistapp.data.model.CoinDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {
    @GET("coins")
    suspend fun getCoinList(): List<Coin>

    @GET("coins/{id}")
    suspend fun getCoinDetail(@Path("id") id: String): CoinDetail
}