package com.necdetzr.loodoscrypto.domain.repository

import com.necdetzr.loodoscrypto.domain.model.Coin

interface CoinRepository {
    suspend fun getCoins(perPage:Int = 10,page:Int = 1) : List<Coin>
}