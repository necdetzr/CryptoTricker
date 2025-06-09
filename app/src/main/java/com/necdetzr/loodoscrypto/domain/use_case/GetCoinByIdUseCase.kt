package com.necdetzr.loodoscrypto.domain.use_case

import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.model.DetailCoin
import com.necdetzr.loodoscrypto.domain.repository.CoinRepository
import javax.inject.Inject


class GetCoinByIdUseCase @Inject constructor(
    private val repository: CoinRepository
){
    suspend operator fun invoke(coinId:String): DetailCoin?{
        return repository.getCoinById(coinId)

    }
}