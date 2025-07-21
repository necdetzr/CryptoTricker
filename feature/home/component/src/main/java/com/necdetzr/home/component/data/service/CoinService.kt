package com.necdetzr.home.component.data.service

import com.necdetzr.home.component.data.model.CoinDto
import com.necdetzr.home.component.data.model.DetailCoinDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CoinService{
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vs_currency:String = "usd",
        @Query("ids") ids:String? = null,
        @Query("order") order:String = "market_cap_desc",
        @Query("per_page") perPage:Int = 10,
        @Query("page") page:Int = 1,
        @Query("sparkline") sparkline:Boolean = false,
    ):List<CoinDto>

    @GET("coins/{id}")
    suspend fun getCoinById(
        @Path("id") id:String,


        ): DetailCoinDto

}