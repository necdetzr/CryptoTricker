package com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.model.DetailCoin
import com.necdetzr.loodoscrypto.domain.use_case.GetCoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    private var requestCount = 0
    private val _coin = MutableStateFlow<DetailCoin?>(null)
    val coin: StateFlow<DetailCoin?> = _coin
    private val _uiState = MutableStateFlow(CoinDetailUiState())
    val uiState : StateFlow<CoinDetailUiState> = _uiState


    init {
        val coinId = savedStateHandle.get<String>("coinId")
        if (coinId != null) {
            getCoinById(coinId)
        }
    }





    fun getCoinById(id: String, refreshTime: Long = 300_000L) {

        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                while (isActive) {
                    requestCount++
                    println("API request getCoinByID #$requestCount for coin $id")

                    _coin.value = getCoinByIdUseCase(id)

                    _uiState.value = _uiState.value.copy(isLoading = false)
                    delay(refreshTime)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

}