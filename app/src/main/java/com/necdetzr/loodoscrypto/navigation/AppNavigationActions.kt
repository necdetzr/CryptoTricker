package com.necdetzr.loodoscrypto.navigation

import androidx.navigation.NavHostController


class AppNavigationActions(private val navController: NavHostController){
    fun navigateToCoinDetail(id:String){
        navController.navigate("detail/$id"){
            launchSingleTop = true
            popUpTo(navController.graph.startDestinationId){saveState = true}
            restoreState = true
        }

    }

    fun navigateToFavorite(){
        navController.navigate("favorites"){
            launchSingleTop = true
            popUpTo(navController.graph.startDestinationId){saveState = true}
            restoreState = true
        }
    }
    fun logOut(){
        navController.navigate("auth"){
            popUpTo(0)
        }
    }
    fun navigateToSearch(){
        navController.navigate(TopLevelRoute.SEARCH.route){
            launchSingleTop = true
            popUpTo(navController.graph.startDestinationId){saveState = true}
            restoreState = true
        }
    }
    fun navigateToMarket(){
        navController.navigate(TopLevelRoute.MARKET.route){
            launchSingleTop = true
            popUpTo(navController.graph.startDestinationId){saveState = true}
        }
    }
}