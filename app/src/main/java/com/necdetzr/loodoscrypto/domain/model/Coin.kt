package com.necdetzr.loodoscrypto.domain.model

import com.necdetzr.loodoscrypto.data.model.Description
import com.necdetzr.loodoscrypto.data.model.High24h


//Readable Model Coin
data class Coin(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val symbol: String = "",
    val currentPrice: Double = 0.0,
    val marketCap: Double = 0.0,
    val totalVolume: Double = 0.0,
    val marketCapRank: Int= 0,
    val priceChangePercentage24h: Double = 0.0
)
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

