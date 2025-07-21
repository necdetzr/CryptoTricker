package com.necdetzr.home.component.domain.repository

import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.component.domain.data.DetailCoin


interface CoinRepository {
    suspend fun getCoins(perPage:Int = 80,page:Int = 1) : List<Coin>
    suspend fun getCoinById(id:String) : DetailCoin?
}