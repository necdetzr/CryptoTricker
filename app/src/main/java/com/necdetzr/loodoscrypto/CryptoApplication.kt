package com.necdetzr.loodoscrypto

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.necdetzr.loodoscrypto.navigation.AppNav
import com.necdetzr.loodoscrypto.presentation.theme.LoodosCryptoTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.necdetzr.datastore.model.DataStoreManager
import com.necdetzr.loodoscrypto.navigation.AppState
import com.necdetzr.loodoscrypto.navigation.BottomNavBar


@Composable
fun CryptoApplication(
    dataStoreManager: DataStoreManager,
    appState: AppState,
    paddingValues: PaddingValues
    ) {
    val darkMode by dataStoreManager.darkMode.collectAsState(initial = false)

    LoodosCryptoTheme(darkTheme = darkMode) {

        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .statusBarsPadding()
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)),
                    bottomBar = {if (appState.shouldShowBar){
                BottomNavBar(
                    appState.navController
                )
            } }
        ) {it

            AppNav(
                dataStoreManager = dataStoreManager,
                appState = appState,
                navController = appState.navController
            )

        }

    }
}