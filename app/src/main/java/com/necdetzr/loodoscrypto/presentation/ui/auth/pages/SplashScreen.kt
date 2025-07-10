package com.necdetzr.loodoscrypto.presentation.ui.auth.pages

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager

@Composable
fun SplashPage(
    dataStoreManager: DataStoreManager,
    onNavigateToMain: () -> Unit,
    onNavigateToAuth: () -> Unit
) {
    val isRememberedState = dataStoreManager.rememberMe.collectAsState(initial = null)

    LaunchedEffect(isRememberedState.value) {
        when (isRememberedState.value) {
            true -> onNavigateToMain()
            false -> onNavigateToAuth()
            null -> {}
        }
    }
}
