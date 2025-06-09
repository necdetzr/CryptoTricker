    package com.necdetzr.loodoscrypto.navigation

    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.necdetzr.loodoscrypto.presentation.ui.components.BottomNavBar
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.home.HomePage
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.market.MarketPage
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile.ProfilePage
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.search.SearchPage
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.detail.CoinDetailPage
    import com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.favorite.FavoritePage


    @Composable
    fun MainNav() {
        val navController = rememberNavController()

        Scaffold(
            containerColor = Color.White,
            bottomBar = {
                BottomNavBar(navController = navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = TopLevelRoute.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(TopLevelRoute.Home.route) { HomePage(navController = navController) }
                composable(TopLevelRoute.Search.route) { SearchPage(navController =navController) }
                composable(TopLevelRoute.Market.route) { MarketPage(navController =navController) }
                composable(TopLevelRoute.Profile.route) { ProfilePage() }
                composable("favorites"){ FavoritePage(navController = navController) }
                composable("detail/{coinId}") { backStackEntry ->
                    val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
                    CoinDetailPage(coinId = coinId, navController = navController)
                }

            }
        }
    }
