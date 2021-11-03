package com.reno.coinlistapp.data

import com.reno.coinlistapp.data.model.Coin
import com.reno.coinlistapp.data.model.CoinDetail
import com.reno.coinlistapp.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CoinRepository {
    suspend fun getCoinList(): Result<List<Coin>>
    suspend fun getCoinDetails(id: String): Result<CoinDetail>
}

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApi
) : CoinRepository {
    override suspend fun getCoinList(): Result<List<Coin>> = withContext(Dispatchers.Default) {
        try {
            val coinList = coinApi.getCoinList()
            Result.Success(coinList)
        } catch (exception: Exception) {
            Result.Error(exception)
        }

    }

    override suspend fun getCoinDetails(id: String) = withContext(Dispatchers.Default) {
        try {
            val coinDefault = coinApi.getCoinDetail(id)
            Result.Success(coinDefault)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}