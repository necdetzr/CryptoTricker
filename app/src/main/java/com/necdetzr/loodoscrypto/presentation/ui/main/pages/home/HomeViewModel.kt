package com.necdetzr.loodoscrypto.presentation.ui.main.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.use_case.GetCoinsUseCase
import com.necdetzr.loodoscrypto.domain.use_case.GetTopCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    //INJECTED USE CASES HERE
    private val getTopCoinsUseCase: GetTopCoinsUseCase
) : ViewModel() {
    private var requestCount = 0

    //Top Coins value with mutable state flow ;)
    private val _topCoins = MutableStateFlow<List<Coin>>(emptyList())
    //Top Coins value with immutable state flow ;)
    val topCoins : StateFlow<List<Coin>> = _topCoins
    //Ui state
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState : StateFlow<HomeUiState> = _uiState

    //Just init..
    init {
        getTopCoins()
    }


    //THIS function gets just top 10 Coin 😢 😢 😢
     fun getTopCoins(){
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                //TRIED GET TOP COINS UPDATE PER 60 SECOND.
                //I USED THIS WAY BECAUSE I DON'T HAVE ANY WEBSOCKET
                val allCoins = withTimeout(5000) {
                    getTopCoinsUseCase()
                }
                while(isActive){
                    _topCoins.value = allCoins.sortedByDescending{ it.marketCap }.take(10)
                    requestCount++
                    println("🔥 API request home GetTOpCoins #$requestCount for coin")
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    delay(60_000)

                }
            }catch (e: Exception){
                //PRINT ERROR IF THERE ANY
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(isError = true)

            }
        }
    }
}