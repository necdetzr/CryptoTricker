package com.necdetzr.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.welcome(
    onNavigateToRegister:()->Unit,
    onNavigateToLogin:()->Unit
){
    composable("welcome") {
        WelcomeScreen(
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToRegister = onNavigateToRegister
        )
    }
}