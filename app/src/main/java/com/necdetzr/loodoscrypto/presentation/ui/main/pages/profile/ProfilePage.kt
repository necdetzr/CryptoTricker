package com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.necdetzr.loodoscrypto.CryptoApp
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.presentation.ui.main.components.ProfileCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.Section
import com.necdetzr.loodoscrypto.presentation.ui.main.components.SettingsCard
import com.necdetzr.loodoscrypto.presentation.ui.main.components.WarningPopUp
import com.necdetzr.loodoscrypto.utils.OptionsFunctions.restartAppWithLocale
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToLogOut: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity
    val coroutineScope = rememberCoroutineScope()
    val analyticsHelper = (context.applicationContext as CryptoApp).analyticsHelper
    //from ProfileViewModel
    val uiState by viewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var openAlertDialog by remember{mutableStateOf(false)}
    //logout burda



    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(contentPadding)
        ) {
            Text("Profile", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(10.dp))
            ProfileCard(
                name = uiState.userName,
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
                onDismissRequest = { expanded = false },
                offset = DpOffset(x = 260.dp, y = (-160).dp)
            ) {
                DropdownMenuItem(
                    text = { Text("English") },
                    onClick = {
                        expanded = false
                        coroutineScope.launch {
                            DataStoreManager(activity.applicationContext).setLanguage("en")
                            restartAppWithLocale(activity = activity, "en")

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
                checked = uiState.darkMode


            )
            Spacer(Modifier.height(12.dp))
            SettingsCard(
                titleIcon = Icons.Rounded.DoorBack,
                title = stringResource(R.string.log_out),
                onClick = {
                    analyticsHelper.logEvent(
                        "logout_card_clicked",
                        mapOf("source" to "profile_page")
                    )
                    openAlertDialog = true
                },
                buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight,
                iconColor = MaterialTheme.colorScheme.error,
                textColor = MaterialTheme.colorScheme.error
            )
            if(openAlertDialog){
                WarningPopUp(
                    onAcceptRequest = {
                        viewModel.logOut(
                            callback = {
                                onNavigateToLogOut()
                            }
                        )
                        openAlertDialog = false

                                      },
                    onDismissRequest = {openAlertDialog = false},
                    title = "Log Out?",
                    text = "Are you sure to log out from application?",
                    firstButton = "Cancel",
                    secondButton = "Log Out"
                )
            }


            Spacer(Modifier.height(32.dp))

            Text(text = uiState.adviceTest)
        }


    }
}

