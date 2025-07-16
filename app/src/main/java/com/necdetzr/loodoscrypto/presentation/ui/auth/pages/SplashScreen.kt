package com.necdetzr.loodoscrypto.presentation.ui.auth.pages

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.necdetzr.datastore.model.DataStoreManager

@Composable
fun SplashPage(
    dataStoreManager: DataStoreManager,
    onNavigateToMain: () -> Unit,
    onNavigateToAuth: () -> Unit
) {
    // TODO: Add Splash Screen with 2 sec delay. 
    val isRememberedState = dataStoreManager.rememberMe.collectAsState(initial = null)
    LaunchedEffect(isRememberedState.value) {
        when (isRememberedState.value) {
            true -> onNavigateToMain()
            false -> onNavigateToAuth()
            null -> {}
        }
    }
}
