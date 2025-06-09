package com.necdetzr.loodoscrypto.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.necdetzr.loodoscrypto.presentation.ui.auth.AuthViewModel
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.LoginPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.RegisterPage
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.WelcomePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.home.HomePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.market.MarketPage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile.ProfilePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.search.SearchPage


@Composable
fun AppNav(authViewModel: AuthViewModel, contentPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {

        navigation(startDestination = "welcome", route = "auth") {
            composable("welcome") {
                WelcomePage(navController)
            }
            composable("login") {
                LoginPage(navController, authViewModel)
            }
            composable("register") {
                RegisterPage(navController)
            }
        }


        composable("main") {
            MainNav()
        }

    }
}

