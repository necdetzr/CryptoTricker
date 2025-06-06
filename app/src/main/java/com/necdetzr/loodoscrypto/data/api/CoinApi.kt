package com.necdetzr.loodoscrypto.data.api

import com.necdetzr.loodoscrypto.data.model.CoinModel
import retrofit2.http.GET
import retrofit2.http.Query


interface CoinApi{
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vs_currency:String = "usd",
        @Query("ids") ids:String? = null,
        @Query("order") order:String = "market_cap_desc",
        @Query("per_page") perPage:Int = 10,
        @Query("page") page:Int = 1,
        @Query("sparkline") sparkline:Boolean = false,
    ):List<CoinModel>
}