package com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import com.necdetzr.loodoscrypto.data.local.FirebaseFirestoreManager
import com.necdetzr.loodoscrypto.domain.model.DetailCoin
import com.necdetzr.loodoscrypto.domain.use_case.GetCoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val authManager: FirebaseAuthManager,
    private val favoriteManager: FirebaseFirestoreManager,
    savedStateHandle: SavedStateHandle

): ViewModel() {


    private val _uiState = MutableStateFlow(CoinDetailUiState())
    val uiState : StateFlow<CoinDetailUiState> = _uiState


    init {
        getUserId()
        val coinId = savedStateHandle.get<String>("coinId")
        if (coinId != null) {
            getCoinById(coinId)
        }
    }





    fun getCoinById(id: String) {

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, isError = false) }
                val coin = getCoinByIdUseCase(id)



                _uiState.update { it.copy(coin = coin, isLoading = false) }


            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }
    private fun getUserId(){
        val id=authManager.getCurrentUserId()
        _uiState.update { it.copy(userId = id) }
    }

    fun addFavorite(id: String, coin: DetailCoin?){
        viewModelScope.launch {
            favoriteManager.addToFavorite(id, coin)
        }
    }
    suspend fun isFavorite(id: String, coinId: String) : Boolean{

        return favoriteManager.isFavorite(id, coinId)

    }
    fun removeFavorite(id:String,coinId: String){
        viewModelScope.launch {
            favoriteManager.removeFromFavorite(id,coinId)
        }
    }

}