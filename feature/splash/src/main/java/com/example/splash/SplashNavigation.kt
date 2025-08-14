package com.example.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.necdetzr.datastore.model.DataStoreManager


fun NavGraphBuilder.splash(
    dataStoreManager: DataStoreManager,
    onNavigateToMain:()->Unit,
    onNavigateToAuth:()->Unit
){
    composable("splash"){
        SplashScreen(
            dataStoreManager = dataStoreManager,
            onNavigateToAuth = onNavigateToAuth,
            onNavigateToMain = onNavigateToMain

        )

    }
}