package com.necdetzr.loodoscrypto.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.home.HomePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.market.MarketPage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile.ProfilePage
import com.necdetzr.loodoscrypto.presentation.ui.main.pages.search.SearchPage


fun NavGraphBuilder.homeSection(
    onNavigateToCoin: (String) -> Unit,
    onNavigateToFavorite: () -> Unit,
    onNavigateToMarket: () -> Unit,
    onNavigateToSearch: () -> Unit,
) {
    composable(TopLevelRoute.HOME.route) {
        HomePage(
            onNavigateToCoin = onNavigateToCoin,
            onNavigateToFavorite = onNavigateToFavorite,
            onNavigateToMarket = onNavigateToMarket,
            onNavigateToSearch = onNavigateToSearch
        )

    }
}

fun NavGraphBuilder.searchSection(
    onNavigateToCoin: (String) -> Unit

) {
    composable(TopLevelRoute.SEARCH.route) {
        SearchPage(
            onNavigateToCoin = onNavigateToCoin
        )
    }

}

fun NavGraphBuilder.marketSection(
    onNavigateToCoin: (String) -> Unit
) {
    composable(TopLevelRoute.MARKET.route) {
        MarketPage(
            onNavigateToCoin = onNavigateToCoin
        )
    }
}

fun NavGraphBuilder.profileSection(
    onNavigateToLogOut: () -> Unit
) {
    composable(TopLevelRoute.PROFILE.route) {
        ProfilePage(onNavigateToLogOut = onNavigateToLogOut)
    }
}