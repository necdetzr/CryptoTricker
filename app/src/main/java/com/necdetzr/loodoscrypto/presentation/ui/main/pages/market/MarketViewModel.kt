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
import javax.inject.Inject


@HiltViewModel
class MarketViewModel @Inject constructor(
    private val getTopGainersUseCase: GetTopGainersUseCase,
    private val getTopLosersUseCase: GetTopLosersUseCase,
    private val getTopCoinsUseCase: GetTopCoinsUseCase

): ViewModel(){
    private val _topGainers = MutableStateFlow<List<Coin>>(emptyList())
    val topGainers: StateFlow<List<Coin>> = _topGainers
    private val _topLosers = MutableStateFlow<List<Coin>>(emptyList())
    val topLosers : StateFlow<List<Coin>> = _topLosers
    private val _topCoins = MutableStateFlow<List<Coin>>(emptyList())
    val topCoins : StateFlow<List<Coin>> = _topCoins
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
                    println("Market Api #$requestCount")

                    _topCoins.value = allCoins.sortedByDescending { it.marketCap }.take(10)
                    _topGainers.value = allCoins.sortedByDescending { it.priceChangePercentage24h }.take(10)
                    _topLosers.value = allCoins.sortedBy { it.priceChangePercentage24h }.take(10)

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

    private fun getTopLosers() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    _uiState.value = _uiState.value.copy(
                        isTopLosersLoading = true,
                        isError = false
                    )

                    val result = withTimeout(5000) {
                        getTopLosersUseCase()
                    }
                    requestCount++
                    println("🔥 API request getTopLosers #$requestCount for coin")
                    _topLosers.value = result
                } catch (e: Exception) {
                    e.printStackTrace()
                    _uiState.value = _uiState.value.copy(isError = true)
                } finally {
                    _uiState.value = _uiState.value.copy(isTopLosersLoading = false)
                }

                delay(300_000)
            }
        }
    }
    private fun getTopCoins() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    _uiState.value = _uiState.value.copy(
                        isTopCoinsLoading = true,
                        isError = false
                    )

                    val result = withTimeout(5000) {
                        getTopCoinsUseCase()
                    }
                    requestCount++
                    println("🔥 API request topCoins #$requestCount for coin")
                    _topCoins.value = result
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