package com.example.login

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister:()->Unit,
    onNavigateToMain:()->Unit
){
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(uiState.loginStatus) {
        when(val status = uiState.loginStatus){
            is LoginStatus.Success -> onNavigateToMain()

            is LoginStatus.Error ->{
                snackbarHostState.showSnackbar(status.message)
            }

            is LoginStatus.Loading->{

            }
            else -> Unit
        }
    }

    LoginPage(
        isLoading = uiState.loading,
        onNavigateToRegister = onNavigateToRegister,
        onLoginClick = {viewModel.onEvent(LoginEvent.OnSignInEvent(uiState.email,uiState.password))},
        checked = uiState.isRemember,
        onCheckedChange = {viewModel.onEvent(LoginEvent.OnUpdateRememberMe(it))},
        onEmailChange = {viewModel.onEvent(LoginEvent.OnEmailChange(it))},
        onPasswordChange = {viewModel.onEvent(LoginEvent.OnPasswordChange(it))},
        email = uiState.email,
        password = uiState.password



    )


}