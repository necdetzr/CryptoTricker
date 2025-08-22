    package com.necdetzr.loodoscrypto.navigation

    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.Stable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.navigation.NavDestination
    import androidx.navigation.NavHostController
    import androidx.navigation.compose.currentBackStackEntryAsState
    import androidx.navigation.compose.rememberNavController


    @Composable
    fun rememberAppState(
        navController:NavHostController = rememberNavController()
    ):AppState{
        return remember(
            navController
        ){
            AppState(
                navController
            )

        }


    }





    @Stable
    class AppState(
        val navController:NavHostController
    ) {
        private val previousDestination = mutableStateOf<NavDestination?>(null)
        val navigationDestination = AppNavigationActions(navController)
        val currentDestination: NavDestination?
            @Composable get(){
                val currentEntry = navController.currentBackStackEntryAsState()

                return currentEntry.value?.destination.also { destination->
                    if (destination != null){
                        previousDestination.value = destination
                    }

                }?: previousDestination.value
            }
        val currentTopLevelRoute:TopLevelRoute?
            @Composable get(){
                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentDestination = navBackStackEntry?.destination
                return TopLevelRoute.entries.firstOrNull { topLevelRoute ->
                    currentDestination?.route == topLevelRoute.route
                }

            }

        val shouldShowBar:Boolean
            @Composable get() = currentTopLevelRoute != null
    }