package com.necdetzr.welcome

import androidx.compose.runtime.Composable


@Composable
fun WelcomeScreen(
    onNavigateToRegister:()->Unit,
    onNavigateToLogin:()->Unit
){
    WelcomePage(
        onNavigateToLogin = onNavigateToLogin,
        onNavigateToRegister = onNavigateToRegister
    )

}