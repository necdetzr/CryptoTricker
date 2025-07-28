package com.example.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToSettings:()->Unit
){
    val uiState by viewModel.uiState.collectAsState()

    ProfilePage(
        name = uiState.name ?: "Guest User",
        surname = uiState.surname ?: "Guest User",
        email = uiState.email ?: "Guest User"
    )

}