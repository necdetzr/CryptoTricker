package com.necdetzr.loodoscrypto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.example.detail.detail
import com.example.favorite.ui.favorite
import com.example.login.login
import com.example.market.market
import com.example.profile.ui.profile
import com.example.register.register
import com.example.search.search
import com.example.splash.splash
import com.necdetzr.datastore.model.DataStoreManager
import com.necdetzr.home.ui.home
import com.necdetzr.language.language
import com.necdetzr.settings.ui.settings
import com.necdetzr.welcome.welcome


@Composable
fun AppNav(dataStoreManager: DataStoreManager,appState: AppState,navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "splash",
        ) {
            navigation(startDestination = "welcome", route = "auth") {
                welcome(
                    onNavigateToLogin = { navController.navigate("login") },
                    onNavigateToRegister = { navController.navigate("register") }
                )

                login(
                    onNavigateToRegister = {
                        navController.navigate("register"){
                            popUpTo("login"){inclusive = true}
                        }
                    },
                    onNavigateToMain = {
                        navController.navigate("main"){
                            popUpTo(0){inclusive = true}
                        }
                    }
                )

                register(
                    onNavigateToLogin = {
                        navController.navigate("login"){
                            popUpTo("register"){inclusive = true}
                        }
                    }
                )
            }

            // MAIN GRAPH
            navigation(startDestination = TopLevelRoute.HOME.route, route = "main") {
                home(
                    onNavigateToCoin = appState.navigationDestination::navigateToCoinDetail,
                    onNavigateToFavorite = appState.navigationDestination::navigateToFavorite,
                    onNavigateToMarket = appState.navigationDestination::navigateToMarket,
                    onNavigateToSearch = appState.navigationDestination::navigateToSearch
                )
                search(
                    onNavigateToCoin = appState.navigationDestination::navigateToCoinDetail
                )
                market(
                    onNavigateToCoin = appState.navigationDestination::navigateToCoinDetail
                )
                profile(

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
                    },
                    navigateLanguage = {
                        navController.navigate("language"){

                        }
                    }




                )
                language(
                    navigateBack = {
                        navController.navigate("settings"){
                            popUpTo("language"){inclusive = true}
                        }
                    }
                )
                detail(


                    onNavigateToBack = {
                        navController.navigate("home")
                    },

                )

                favorite(
                    onBackButton = {
                        navController.navigate("home")
                    }

                )

            }
            splash(
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

