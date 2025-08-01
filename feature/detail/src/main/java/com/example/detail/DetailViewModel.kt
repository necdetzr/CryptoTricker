package com.example.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.home.component.domain.usecase.GetCoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    //private val favoriteManager: FirebaseFirestoreManager,
    savedStateHandle: SavedStateHandle
): BaseViewModel<DetailViewState>(){
    override fun createInitialState(): DetailViewState = DetailViewState()
    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }



    init {
        val coinId = savedStateHandle.get<String>("coinId")
        if (coinId != null) {
            getCoinById(coinId)
        }

    }




    private fun getCoinById(coinId:String){
        viewModelScope.launch {
            showLoading(true)
            try {
                val coin = getCoinByIdUseCase(coinId)
                setState { copy(coin = coin) }
            }catch (e: Exception){
                Timber.e("A error occurred while gettin detail Coin:$e")
                setState { copy(showErrorModal = true) }
            }
            showLoading(false)
        }
    }
//    fun addFavorite(id: String, coin: DetailCoin?){
//        viewModelScope.launch {
//            favoriteManager.addToFavorite(id, coin)
//        }
//    }
//    suspend fun isFavorite(id: String, coinId: String) : Boolean{
//
//        return favoriteManager.isFavorite(id, coinId)
//
//    }
//    fun removeFavorite(id:String,coinId: String){
//        viewModelScope.launch {
//            favoriteManager.removeFromFavorite(id,coinId)
//        }
//    }

}