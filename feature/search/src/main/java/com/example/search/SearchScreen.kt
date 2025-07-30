package com.example.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToCoin:(String)->Unit
){
    val uiState by viewModel.uiState.collectAsState()
    SearchPage(
        onValueChange = {viewModel.onEvent(SearchEvent.OnSearchQuery(it))},
        searchQuery = uiState.searchQuery,
        isLoading = uiState.loading,
        isError = uiState.showErrorModal,
        onNavigateToCoin = onNavigateToCoin,
        coins = uiState.coins
    )
}