package com.necdetzr.loodoscrypto.domain.use_case

import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.repository.CoinRepository
import javax.inject.Inject

class GetTopGainersUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() : List<Coin>{
        val allCoins = repository.getCoins(perPage = 160, page = 1)
        return allCoins
            .sortedByDescending { it.priceChangePercentage24h }
            .take(10)
    }
}