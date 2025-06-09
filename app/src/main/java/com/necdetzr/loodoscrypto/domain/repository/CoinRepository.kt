package com.necdetzr.loodoscrypto.domain.repository

import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.model.DetailCoin

interface CoinRepository {
    suspend fun getCoins(perPage:Int = 80,page:Int = 1) : List<Coin>
    suspend fun getCoinById(id:String) : DetailCoin?
}