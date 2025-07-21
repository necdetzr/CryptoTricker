package com.necdetzr.home.component.domain.usecase

import com.necdetzr.home.component.domain.data.DetailCoin
import com.necdetzr.home.component.domain.repository.CoinRepository
import javax.inject.Inject


class GetCoinByIdUseCase @Inject constructor(
    private val repository: CoinRepository
){
    suspend operator fun invoke(coinId:String): DetailCoin?{
        return repository.getCoinById(coinId)

    }
}