package com.necdetzr.loodoscrypto.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.settings.ui.settings
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.presentation.ui.auth.pages.SplashPage
import com.necdetzr.loodoscrypto.presentation.ui.components.BottomNavBar
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.detail.CoinDetailPage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.favorite.FavoritePage



@Composable
fun AppNav(dataStoreManager: DataStoreManager) {
    val navController = rememberNavController()
    val appState = rememberAppState(navController)

    Scaffold(
        bottomBar = {
            if (appState.shouldShowBar) {
                BottomNavBar(appState.navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            // AUTH GRAPH
            navigation(startDestination = "welcome", route = "auth") {
                welcome(
                    onNavigateToLogin = { navController.navigate("login") },
                    onNavigateToRegister = { navController.navigate("register") }
                )

                login(
                    onNavigateToLogin = { navController.navigate("login") },
                    onNavigateToMain = {
                        navController.navigate("main") {
                            popUpTo("auth") { inclusive = true }
                        }
                    }
                )

                register(
                    onNavigateToLogin = { navController.popBackStack("login", false) },
                    onNavigateToMain = {
                        navController.navigate("main") {
                            popUpTo("auth") { inclusive = true }
                        }
                    }
                )
            }

            // MAIN GRAPH
            navigation(startDestination = TopLevelRoute.HOME.route, route = "main") {
                homeSection(
                    onNavigateToCoin = appState.navigationDestination::navigateToCoinDetail,
                    onNavigateToFavorite = appState.navigationDestination::navigateToFavorite,
                    onNavigateToMarket = appState.navigationDestination::navigateToMarket,
                    onNavigateToSearch = appState.navigationDestination::navigateToSearch
                )
                searchSection(
                    onNavigateToCoin = appState.navigationDestination::navigateToCoinDetail
                )
                marketSection(
                    onNavigateToCoin = appState.navigationDestination::navigateToCoinDetail
                )
                profileSection(
                    onNavigateToLogOut = {
                        navController.navigate("auth") {
                            popUpTo("main") { inclusive = true }
                        }
                    },
                    onNavigateToSettings = {
                        navController.navigate("settings")
                    }
                )
                settings(
                    navigateLogin = {
                        navController.navigate("welcome"){
                            popUpTo("main"){inclusive = true}
                        }
                    },
                    navigateBack = {
                        navController.navigate("profile"){
                            popUpTo("settings"){inclusive = true}
                        }
                    }



                )

                composable("favorites") {
                    FavoritePage(navController = navController)
                }
                composable("detail/{coinId}") { backStackEntry ->
                    val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
                    CoinDetailPage(coinId = coinId, navController = navController)
                }
            }


            composable("splash") {
                SplashPage(
                    dataStoreManager = dataStoreManager,
                    onNavigateToMain = {
                        navController.navigate("main") {
                            popUpTo("splash") { inclusive = true }
                        }
                    },
                    onNavigateToAuth = {
                        navController.navigate("auth") {
                            popUpTo("splash") { inclusive = true }
                        }
                    }
                )
            }

        }
    }
}
