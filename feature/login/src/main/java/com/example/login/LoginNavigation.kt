package com.example.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.login(
    onNavigateToRegister:()->Unit
){
composable("login") {
    LoginScreen(
        onNavigateToRegister = onNavigateToRegister
    )
}
}