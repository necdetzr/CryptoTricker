package com.necdetzr.home.component.domain.data

data class Coin(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val symbol: String = "",
    val currentPrice: Double = 0.0,
    val marketCap: Double = 0.0,
    val totalVolume: Double = 0.0,
    val marketCapRank: Int = 0,
    val priceChangePercentage24h: Double = 0.0
)