package com.necdetzr.loodoscrypto.presentation.ui.main.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.use_case.GetTopCoinsUseCase
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
class HomeViewModel @Inject constructor(
    //INJECTED USE CASES HERE
    private val getTopCoinsUseCase: GetTopCoinsUseCase
) : ViewModel() {
    private var requestCount = 0


    //Ui state
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState : StateFlow<HomeUiState> = _uiState

    //Just init..
    init {
        getTopCoins()
    }

    //view state oluştur
    //THIS function gets just top 10 Coin
    private fun getTopCoins(){
        viewModelScope.launch {
            try {
               _uiState.value=_uiState.value.copy(isLoading = true)

                val allCoins = withTimeout(5000) {
                    getTopCoinsUseCase()
                }
                while(isActive){
                    _uiState.value=_uiState.value.copy(coins =allCoins.sortedByDescending{ it.marketCap }.take(10) )

                    requestCount++
                    Timber.i("API request home GetTopCoins $requestCount for coin")
                    _uiState.value=_uiState.value.copy(isLoading = false)
                    delay(60_000)

                }

            }catch (e: Exception){
                Timber.e(e)
                _uiState.value= _uiState.value.copy(isError = true)

            }
        }

    }
}