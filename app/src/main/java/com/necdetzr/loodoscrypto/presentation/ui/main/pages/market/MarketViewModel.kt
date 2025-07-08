package com.necdetzr.loodoscrypto.presentation.ui.main.pages.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.use_case.GetTopCoinsUseCase
import com.necdetzr.loodoscrypto.domain.use_case.GetTopGainersUseCase
import com.necdetzr.loodoscrypto.domain.use_case.GetTopLosersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MarketViewModel @Inject constructor(

    private val getTopCoinsUseCase: GetTopCoinsUseCase

): ViewModel(){
    private val _uiState = MutableStateFlow(MarketUiState())
    val uiState : StateFlow<MarketUiState> = _uiState
    private var requestCount = 0

    init{
         loadMarketData()
    }

    private fun loadMarketData() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    _uiState.value = _uiState.value.copy(
                        isTopCoinsLoading = true,
                        isError = false
                    )

                    val allCoins = withTimeout(5000) {
                        getTopCoinsUseCase()
                    }

                    requestCount++
                    Timber.d("Request market: $requestCount")

                    _uiState.value = _uiState.value.copy(topLosers = allCoins.sortedBy { it.priceChangePercentage24h }.take(10))
                    _uiState.value =_uiState.value.copy(topGainers = allCoins.sortedByDescending { it.priceChangePercentage24h }.take(10))
                    _uiState.value = _uiState.value.copy(topCoins = allCoins.sortedByDescending { it.marketCap }.take(10))

                } catch (e: Exception) {
                    e.printStackTrace()
                    _uiState.value = _uiState.value.copy(isError = true)
                } finally {
                    _uiState.value = _uiState.value.copy(isTopCoinsLoading = false)
                }

                delay(300_000)
            }
        }
    }
}