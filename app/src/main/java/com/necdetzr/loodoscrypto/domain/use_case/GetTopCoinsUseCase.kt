package com.necdetzr.loodoscrypto.domain.use_case

import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.repository.CoinRepository
import javax.inject.Inject

class GetTopCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(): List<Coin>{
        return repository.getCoins(perPage = 10,page = 1)
    }
}