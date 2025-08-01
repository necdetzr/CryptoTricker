package com.example.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onNavigateToBack:()->Unit
){
    val uiState by viewModel.uiState.collectAsState()

    DetailPage(
        onBackButton = onNavigateToBack,
        onAddFavorite = {},
        onRemoveFavorite = {},
        isError = uiState.showErrorModal,
        isLoading = uiState.loading,
        coin = uiState.coin

    )
}