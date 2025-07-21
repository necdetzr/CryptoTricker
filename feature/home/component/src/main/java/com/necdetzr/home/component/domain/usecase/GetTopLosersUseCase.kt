package com.necdetzr.home.component.domain.usecase


import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.component.domain.repository.CoinRepository
import javax.inject.Inject

class GetTopLosersUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(): List<Coin>{
        val allCoins = repository.getCoins(perPage = 160, page = 1)
        return allCoins
            .sortedBy { it.priceChangePercentage24h }
            .take(10)
    }
}