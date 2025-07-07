    package com.necdetzr.loodoscrypto.navigation

    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import com.necdetzr.loodoscrypto.presentation.ui.components.BottomNavBar
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.detail.CoinDetailPage
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.favorite.FavoritePage


    @Composable
    fun MainNav(
        logOut:()->Unit
    ) {
        val appState = rememberAppState()

        Scaffold(
            containerColor = Color.White,
            bottomBar = {
                if(appState.shouldShowBar){
                    BottomNavBar(appState.navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = TopLevelRoute.HOME.route,
                modifier = Modifier.padding(innerPadding)
            ) {
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
                profileSection({logOut()})


                composable("favorites"){ FavoritePage(navController = appState.navController) }
                composable("detail/{coinId}") { backStackEntry ->
                    val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
                    CoinDetailPage(coinId = coinId, navController = appState.navController)
                }

            }
        }
    }
