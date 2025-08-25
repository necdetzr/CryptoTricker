package com.example.market

import androidx.lifecycle.viewModelScope
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.home.component.domain.usecase.GetCoinsUseCase
import com.necdetzr.home.component.domain.usecase.GetTopCoinsUseCase
import com.necdetzr.home.component.domain.usecase.GetTopGainersUseCase
import com.necdetzr.home.component.domain.usecase.GetTopLosersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MarketViewModel @Inject constructor(

    private val getTopCoinsUseCase: GetTopCoinsUseCase

): BaseViewModel<MarketViewState>() {
    override fun createInitialState(): MarketViewState = MarketViewState()
    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    init {
        getTopCoins()
    }


     fun getTopCoins(){
        viewModelScope.launch {
            showLoading(true)
            try {
                val topCoins = getTopCoinsUseCase()
                setState { copy(topCoins =topCoins) }
                setState { copy(topLosers = topCoins.sortedBy { it.priceChangePercentage24h }.take(10)) }
                setState {copy(topGainers = topCoins.sortedByDescending { it.priceChangePercentage24h }.take(10)) }
            }catch (e: Exception){
                Timber.e("An error occupied while getting TopCoins: $e")
                setState { copy(showErrorModal = true) }
            }
            showLoading(false)
        }
    }

}