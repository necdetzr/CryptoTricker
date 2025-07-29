package com.example.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister:()->Unit
){
    val uiState by viewModel.uiState.collectAsState()

    LoginPage(
        isLoading = uiState.loading,
        onNavigateToRegister = onNavigateToRegister,
        onLoginClick = {viewModel.onEvent(LoginEvent.OnSignInEvent(uiState.email,uiState.password))},
        checked = uiState.isRemember,
        onCheckedChange = {viewModel.onEvent(LoginEvent.OnSetRememberMe)}

    )

}