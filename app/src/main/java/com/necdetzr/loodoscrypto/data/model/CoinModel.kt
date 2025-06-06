package com.necdetzr.loodoscrypto.data.model

import com.necdetzr.loodoscrypto.domain.model.Coin

data class CoinModel(
    val id: String,
    val symbol: String,
    val name:String,
    val image: String,
    val current_price: Double,
    val market_cap:Double,
    val market_cap_rank:Double,
    val total_volume: Double,
    val high_24h:Double,
    val low_24h:Double,
    val price_change_24h:Double,
    val price_change_percentage_24h:Double,
    val total_supply:Double,
    val max_supply:Double,
    val ath:Double,
    val ath_change_percentage:Double


)
fun CoinModel.toDomain(): Coin {
    return Coin(
        id = this.id,
        image = this.image,
        name = this.name,
        symbol = this.symbol,
        currentPrice = this.current_price,
        marketCap = this.market_cap,
        totalVolume = this.total_volume,
        marketCapRank = this.market_cap_rank.toInt(),
        priceChangePercentage24h = this.price_change_percentage_24h

    )
}