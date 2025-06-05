package com.necdetzr.loodoscrypto.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.LoginPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.RegisterPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.WelcomePage

@Composable
fun AuthNav(contentPadding: PaddingValues) {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "welcome",
        modifier = Modifier.padding(contentPadding)
    ) {
        composable("welcome") {
            WelcomePage(navController)
        }
        composable("login") {
            LoginPage()
        }
        composable("register") {
            RegisterPage()
        }

    }


}