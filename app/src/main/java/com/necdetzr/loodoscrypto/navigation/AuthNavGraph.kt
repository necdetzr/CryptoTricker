package com.necdetzr.loodoscrypto.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.LoginPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.RegisterPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.WelcomePage



fun NavGraphBuilder.welcome(
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
){
    composable("welcome") {
        WelcomePage(onNavigateToRegister=onNavigateToRegister,onNavigateToLogin=onNavigateToLogin)

    }

}
fun NavGraphBuilder.login(
    onNavigateToLogin: () -> Unit,
    onNavigateToMain:()->Unit,

){
    composable("login") {
        LoginPage(onNavigateToLogin = onNavigateToLogin, onNavigateToMain = onNavigateToMain)
    }

}
fun NavGraphBuilder.register(
    onNavigateToLogin:()->Unit,
    onNavigateToMain:()->Unit,
){
    composable("register"){
        RegisterPage(onNavigateToMain = onNavigateToMain, onNavigateToLogin = onNavigateToLogin)
    }
}