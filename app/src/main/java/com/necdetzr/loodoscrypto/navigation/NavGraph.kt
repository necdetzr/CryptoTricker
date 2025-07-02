package com.necdetzr.loodoscrypto.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.home.HomePage


fun NavGraphBuilder.homeSection(
    navController: NavHostController
){
    composable("home"){
        HomePage(
            onNavigateToCoin = {

            },
            onNavigateToFavorite = {

            },
            onNavigateToMarket = {

            },
            onNavigateToSearch = {

            }
        )

    }
}