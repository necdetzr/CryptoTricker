package com.example.register

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.register(
    onNavigateToLogin:()->Unit
){
    composable("register"){
        RegisterScreen(
            onNavigateToLogin = onNavigateToLogin
        )
    }
}