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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : ViewModel(){

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState : StateFlow<SearchUiState> = _uiState


    @OptIn(FlowPreview::class)
    val filteredCoins: StateFlow<List<Coin>> = _uiState
        .map {  state->
            val query = state.searchQuery
            val coins = state.coins

            if (query.isBlank()) coins
            else coins.filter {
                it.name.contains(query, ignoreCase = true) || it.symbol.contains(query, ignoreCase = true)
            }
        }
        .debounce (300L)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    init {
        getCoins()
    }

    fun onSearchQueryChanged(query:String){
        _uiState.update { it.copy(searchQuery = query) }
    }


    private fun getCoins(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, isError = false) }
            try {
                val coins = getCoinsUseCase()
                _uiState.update { it.copy(
                    coins = coins,
                    isLoading = false
                ) }
            }catch (e: Exception){
                _uiState.update { it.copy(isLoading = false, isError = true) }
                Timber.d(e,"Error during getCoins")
            }
        }

    }



}