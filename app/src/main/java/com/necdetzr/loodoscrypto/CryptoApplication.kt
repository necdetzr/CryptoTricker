package com.necdetzr.loodoscrypto

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.navigation.AppNav
import com.necdetzr.loodoscrypto.presentation.theme.LoodosCryptoTheme
import com.necdetzr.loodoscrypto.presentation.ui.auth.AuthViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier


@Composable
fun CryptoApplication(authViewModel: AuthViewModel,contentPaddingValues: PaddingValues,dataStoreManager: DataStoreManager) {
    val darkMode by dataStoreManager.darkMode.collectAsState(initial = false)

    LoodosCryptoTheme(darkTheme = darkMode) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNav(authViewModel,contentPaddingValues,dataStoreManager)

        }

    }
}