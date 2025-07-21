package com.necdetzr.home.component.data.repository

import com.necdetzr.home.component.data.model.toDomain
import com.necdetzr.home.component.data.service.CoinService
import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.component.domain.data.DetailCoin
import com.necdetzr.home.component.domain.repository.CoinRepository
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val api: CoinService
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

