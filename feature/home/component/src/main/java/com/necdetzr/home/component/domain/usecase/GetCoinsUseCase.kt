package com.necdetzr.home.component.domain.usecase


import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.component.domain.repository.CoinRepository
import javax.inject.Inject


class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
){
    suspend operator fun invoke():List<Coin>{
        return repository.getCoins(perPage = 160, page = 1)

    }
}