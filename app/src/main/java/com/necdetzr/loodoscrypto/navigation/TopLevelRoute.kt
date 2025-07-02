package com.necdetzr.loodoscrypto.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.necdetzr.loodoscrypto.R

//Routes for Bottom Navigation Bar
/*sealed class TopLevelRoute(
    val route:String,
    val icon: ImageVector,
    val label:Int
) {
    object Home : TopLevelRoute("home",Icons.Rounded.Home, R.string.home)
    object Search : TopLevelRoute("search",Icons.Outlined.Search,R.string.search)
    object Market : TopLevelRoute("market", Icons.Rounded.TrendingUp,R.string.market)
    object Profile : TopLevelRoute("profile",Icons.Outlined.Person,R.string.profile)
}*/

enum class TopLevelRoute(
    val route:String,
    val icon:ImageVector,
    val label:Int
){
    HOME(
        route = "home",
        icon = Icons.Rounded.Home,
        label = R.string.name
    ),
    SEARCH(
        route = "search",
        icon = Icons.Rounded.Search,
        label = R.string.search
    ),
    MARKET(
        route="market",
        icon = Icons.Rounded.TrendingUp,
        label = R.string.market
    ),
    PROFILE(
        route = "profile",
        icon = Icons.Rounded.Person,
        label = R.string.profile
    )
}