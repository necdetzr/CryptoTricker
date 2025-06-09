package com.necdetzr.loodoscrypto.presentation.ui.main.pages.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.domain.model.Coin
import com.necdetzr.loodoscrypto.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : ViewModel(){
    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins : StateFlow<List<Coin>> = _coins
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState : StateFlow<SearchUiState> = _uiState
    private val _searchQuery = MutableStateFlow("")
    val searchQuery : StateFlow<String> = _searchQuery
    private var requestCount = 0

    @OptIn(FlowPreview::class)
    val filteredCoins: StateFlow<List<Coin>> = _searchQuery
        .debounce(300L).
        combine(_coins) { query, coins ->
        if (query.isBlank()) coins
        else coins.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.symbol.contains(query, ignoreCase = true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    init {
        getCoins()
    }

    fun onSearchQueryChanged(query:String){
        _searchQuery.value = query
    }


    private fun getCoins(){
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                while(isActive){
                    _coins.value = getCoinsUseCase()
                    requestCount++
                    println("API request getCoins #$requestCount for coin")
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    delay(300_000)
                }
            }catch(e: Exception){
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(isError = true)

            }
        }

    }



}