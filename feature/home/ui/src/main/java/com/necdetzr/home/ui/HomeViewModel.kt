package com.necdetzr.home.ui

import androidx.lifecycle.viewModelScope
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.home.component.domain.usecase.GetTopCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopCoinsUseCase: GetTopCoinsUseCase
) : BaseViewModel<HomeViewState>(){
    override fun createInitialState(): HomeViewState = HomeViewState()
    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }


    init {
        getCoins()
    }
    fun getCoins(){
        viewModelScope.launch {
            showLoading(true)
            try {
                val topCoins = getTopCoinsUseCase()
                setState { copy(topCoins = topCoins.sortedByDescending { it.marketCap }.take(10)) }
            }catch (e: Exception){
                setState { copy(showErrorModal = true) }
                Timber.e("Error while taken topCoins in Home Page")
            }

            showLoading(false)

        }
    }
}