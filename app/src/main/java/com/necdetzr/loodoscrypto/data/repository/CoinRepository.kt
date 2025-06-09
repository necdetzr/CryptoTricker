package com.necdetzr.loodoscrypto.data.repository

import com.necdetzr.loodoscrypto.data.api.CoinApi
import com.necdetzr.loodoscrypto.data.model.CoinModel
import com.necdetzr.loodoscrypto.data.model.toDomain
import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.model.DetailCoin
import com.necdetzr.loodoscrypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
): CoinRepository {
    override suspend fun getCoins(perPage:Int, page:Int): List<Coin> {
        val apiList = api.getCoins(perPage = perPage,page = page)
        return apiList.map{it.toDomain()}
    }


    override suspend fun getCoinById(id: String): DetailCoin {
        val coin = api.getCoinById(id)
        return coin.toDomain()
    }
}

