package com.necdetzr.home.component.domain.data

data class DetailCoin(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val symbol: String = "",
    val currentPrice: Double = 0.0,
    val description: String = "",
    val marketCap: Double = 0.0,
    val totalSupply: Double = 0.0,
    val maxSupply: Double = 0.0,
    val high24h: Double = 0.0,
    val low24h: Double = 0.0,
    val totalVolume: Double = 0.0,
    val marketCapRank: Int = 0,
    val hashAlgorithm : String = "",
    val priceChangePercentage24h: Double = 0.0,
    var isFavorite : Boolean = false
)
