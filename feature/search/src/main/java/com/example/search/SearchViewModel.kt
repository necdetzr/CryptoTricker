package com.example.search

import androidx.lifecycle.viewModelScope
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.home.component.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
   private val getAllCoinsUseCase: GetCoinsUseCase
): BaseViewModel<SearchViewState>(){
    override fun createInitialState(): SearchViewState = SearchViewState()
    override fun showLoading(isLoading: Boolean)= setState { copy(loading=isLoading) }

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.OnSearchQuery -> onSearchQuery(event.query)
        }

    }

    init {
        getCoins()
    }

    private fun getCoins(){
        viewModelScope.launch {
            showLoading(true)
            try {
                val allCoins = getAllCoinsUseCase()
                setState { copy(coins = allCoins) }
            }catch (e: Exception){
                Timber.e("Error excepted in Search Page: $e")
            }
        }
    }

    fun onSearchQuery(query:String){
        setState { copy(searchQuery = query) }
    }



}