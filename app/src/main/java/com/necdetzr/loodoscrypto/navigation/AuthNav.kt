package com.necdetzr.loodoscrypto.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.necdetzr.loodoscrypto.presentation.ui.auth.AuthViewModel
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.LoginPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.RegisterPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.WelcomePage

@Composable
fun AuthNav(contentPadding: PaddingValues,authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    Scaffold(
        containerColor = androidx.compose.ui.graphics.Color.White
    ) { innerPadding->
        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("welcome") {
                WelcomePage(navController)
            }
            composable("login") {
                LoginPage(navController,authViewModel)
            }
            composable("register") {
                RegisterPage(navController)
            }


        }

    }



}