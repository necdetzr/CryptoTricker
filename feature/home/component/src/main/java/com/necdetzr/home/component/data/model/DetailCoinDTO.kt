package com.necdetzr.home.component.data.model

import com.necdetzr.home.component.domain.data.DetailCoin


data class DetailCoinDto(
    val id: String,
    val symbol: String,
    val name: String,
    val description: Description,
    val image: ImageUrls,
    val market_cap_rank: Int?,
    val market_data: MarketData?,
    val hashing_algorithm: String?,
    val last_updated: String
)

data class Description(val en: String)
data class ImageUrls(val thumb: String, val small: String, val large: String)
data class CurrentPrice(val usd: Double)
data class MarketCap(val usd: Double)
data class TotalVolume(val usd: Double)
data class High24h(val usd: Double)
data class Low24h(val usd: Double)

data class MarketData(
    val current_price: CurrentPrice,
    val market_cap: MarketCap,
    val total_volume: TotalVolume,
    val price_change_percentage_24h: Double,
    val total_supply: Double?,
    val high_24h: High24h,
    val low_24h: Low24h,
    val max_supply: Double?,
    val circulating_supply: Double
)
fun DetailCoinDto.toDomain(): DetailCoin {
    return DetailCoin(
        id = this.id,
        image = this.image.large,
        name = this.name,
        symbol = this.symbol,
        hashAlgorithm = this.hashing_algorithm?: "No Algorithm",
        description = this.description.en,
        currentPrice = this.market_data?.current_price?.usd ?: 0.0,
        marketCap = this.market_data?.market_cap?.usd ?: 0.0,
        totalSupply = this.market_data?.total_supply ?: 0.0,
        totalVolume = this.market_data?.total_volume?.usd ?: 0.0,
        marketCapRank = this.market_cap_rank ?: 0,
        maxSupply = this.market_data?.max_supply ?: 0.0,
        high24h = this.market_data?.high_24h?.usd ?: 0.0,
        low24h = this.market_data?.low_24h?.usd ?: 0.0,
        priceChangePercentage24h = this.market_data?.price_change_percentage_24h ?: 0.0
    )
}