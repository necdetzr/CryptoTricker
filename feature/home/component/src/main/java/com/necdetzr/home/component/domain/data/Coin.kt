package com.necdetzr.home.component.domain.data

data class Coin(
    val id: String,
    val image: String,
    val name: String,
    val symbol: String,
    val currentPrice: Double,
    val marketCap: Double,
    val totalVolume: Double,
    val marketCapRank: Int,
    val priceChangePercentage24h: Double
)