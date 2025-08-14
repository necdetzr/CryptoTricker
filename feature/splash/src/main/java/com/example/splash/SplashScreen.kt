package com.example.splash

import androidx.compose.runtime.Composable
import com.necdetzr.datastore.model.DataStoreManager


@Composable
fun SplashScreen(
    dataStoreManager: DataStoreManager,
    onNavigateToMain:()-> Unit,
    onNavigateToAuth:()->Unit
) {
    SplashPage(
        onNavigateToAuth = onNavigateToAuth,
        onNavigateToMain = onNavigateToMain,
        dataStoreManager = dataStoreManager
    )
}