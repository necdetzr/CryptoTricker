package com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.DoorBack
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.presentation.ui.components.CrashButton
import com.necdetzr.loodoscrypto.presentation.ui.main.components.ProfileCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.Section
import com.necdetzr.loodoscrypto.presentation.ui.main.components.SettingsCard
import com.necdetzr.loodoscrypto.presentation.ui.main.viewmodels.RemoteConfigViewModel
import com.necdetzr.loodoscrypto.utils.OptionsFunctions.restartAppWithLocale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(
    viewModel: ProfileViewModel = hiltViewModel(),
    remoteViewModel : RemoteConfigViewModel = hiltViewModel(),
    onNavigateToLogOut:()->Unit
){
    val context = LocalContext.current
    val activity = context as Activity
    val coroutineScope = rememberCoroutineScope()
    //from ProfileViewModel
    val darkMode by viewModel.darkMode.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    //Crashlytics
    val crashlytics = FirebaseCrashlytics.getInstance()
    val adviceText by remoteViewModel.adviceTest.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { contentPadding->
        Column(
            modifier = Modifier.padding(16.dp).padding(contentPadding)
        ) {
            Text("Profile", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(10.dp))
            ProfileCard(
                name = uiState.userName ,
                email = uiState.email
            )
            Spacer(Modifier.height(20.dp))
            Section(stringResource(R.string.general_settings))
            Spacer(Modifier.height(12.dp))
            SettingsCard(
                titleIcon = Icons.Rounded.Language,
                title = stringResource(R.string.language_settings),
                onClick = {
                    expanded = true
                },
                buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                offset = DpOffset(x = 260.dp, y = (-160).dp)
            ) {
                DropdownMenuItem(
                    text = {Text("English")},
                    onClick = {
                        expanded = false
                        coroutineScope.launch {
                            DataStoreManager(activity.applicationContext).setLanguage("en")
                            restartAppWithLocale(activity =activity,"en" )

                        }

                    }
                )
                DropdownMenuItem(
                    text = { Text("Türkçe") },
                    onClick = {
                        expanded = false
                        coroutineScope.launch {
                            DataStoreManager(activity.applicationContext).setLanguage("tr")
                            restartAppWithLocale(activity, "tr")

                        }
                    }
                )
            }
            Spacer(Modifier.height(12.dp))

            SettingsCard(
                titleIcon = Icons.Rounded.DarkMode,
                title = stringResource(R.string.dark_mode),
                onClick = {

                },
                buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight,
                iconColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                switch = true,
                onCheckedChange = {

                    coroutineScope.launch {
                        viewModel.switchDarkMode()


                    }
                },
                checked = darkMode


            )
            Spacer(Modifier.height(12.dp))
            SettingsCard(
                titleIcon = Icons.Rounded.DoorBack,
                title = stringResource(R.string.log_out),
                onClick = {
                    viewModel.logOut()
                    onNavigateToLogOut()
                },
                buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight,
                iconColor = MaterialTheme.colorScheme.error,
                textColor = MaterialTheme.colorScheme.error
            )


            Spacer(Modifier.height(32.dp))
            CrashButton(
                onClick = {
                    val city = "Izmir"
                    crashlytics.setCustomKeys {
                        key("city",city)
                        key("module","main_module")
                    }
                    crashlytics.log("Crash detected: $city")

                    throw RuntimeException("Test Crash")

                }
            )
            Text(text = adviceText)
            }


        }
    }

