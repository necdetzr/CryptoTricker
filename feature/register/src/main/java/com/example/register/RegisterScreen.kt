package com.example.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin:()->Unit
){
    val uiState by viewModel.uiState.collectAsState()

    RegisterPage(
        isLoadings = uiState.loading,
        onRegisterClick = {viewModel.onEvent(RegisterEvent.OnSignInEvent(uiState.name,uiState.surname,uiState.email,uiState.password))},
        onNavigateToLogin = onNavigateToLogin

    )
}