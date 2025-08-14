package com.example.search

import androidx.lifecycle.viewModelScope
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.component.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private fun getCoins() {
        viewModelScope.launch {
            showLoading(true)
            runCatching { getAllCoinsUseCase() }
                .onSuccess { handleCoinsLoaded(it) }
            showLoading(false)
        }
    }

    private fun handleCoinsLoaded(allCoins: List<Coin>) {
        val filtered = filterList(
            allCoins,uiState.value.searchQuery
        )
        setState {
            copy(
                coins = allCoins,
                filteredCoins = filtered
            )
        }
    }


    private fun filterList(
        coins: List<Coin>,
        query: String
    ) : List<Coin>{
        return if(query.isEmpty()){
            coins
        }else {
            coins.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.symbol.contains(query, ignoreCase = true)
            }
        }

    }

    fun onSearchQuery(query:String){
        val filtered = filterList(uiState.value.coins, query)
        setState {
            copy(
                searchQuery = query,
                filteredCoins =filtered
            )
        }
    }

}