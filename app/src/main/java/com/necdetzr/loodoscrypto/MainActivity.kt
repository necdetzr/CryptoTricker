package com.necdetzr.loodoscrypto

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.navigation.AppNav
import com.necdetzr.loodoscrypto.presentation.theme.LoodosCryptoTheme
import com.necdetzr.loodoscrypto.presentation.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    private lateinit var dataStoreManager: DataStoreManager

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            dataStoreManager = DataStoreManager(it)
            runBlocking {
                val langCode = dataStoreManager.languageFlow.first() ?: "en"
                val locale = Locale(langCode)
                Locale.setDefault(locale)
                val config = Configuration(it.resources.configuration)
                config.setLocale(locale)
                applyOverrideConfiguration(config)
            }
        }
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel : AuthViewModel = hiltViewModel()

            LoodosCryptoTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->

                    CryptoApplication(authViewModel,innerPadding,dataStoreManager)
                }
            }
        }
    }
}

