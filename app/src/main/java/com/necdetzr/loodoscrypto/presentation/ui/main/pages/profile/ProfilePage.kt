package com.necdetzr.loodoscrypto.presentation.ui.main.pages.profile

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.DoorBack
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
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
import com.necdetzr.loodoscrypto.utils.OptionsFunctions
import com.necdetzr.loodoscrypto.utils.OptionsFunctions.restartAppWithLocale
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(viewModel: ProfileViewModel = hiltViewModel()){
    val context = LocalContext.current

    val activity = context as Activity

    var expanded by remember { mutableStateOf(false) }
    val userName by viewModel.userName.collectAsState()
    val userEmail by viewModel.userEmail.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    //Crashlytics
    val crashlytics = FirebaseCrashlytics.getInstance()


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Profile", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(10.dp))
        ProfileCard(
            name = userName ?: "New Guest User",
            email = userEmail ?: ""
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
            titleIcon = Icons.Rounded.DoorBack,
            title = stringResource(R.string.log_out),
            onClick = {
                viewModel.logOut()
                activity.finish()
            },
            buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight,
            iconColor = Color(0xFF5D0000),
            textColor = Color(0xFF5D0000)
        )
        Spacer(Modifier.height(12.dp))
        CrashButton(
            onClick = {
                val city = "Istanbul"
                crashlytics.setCustomKeys {
                    key("city",city)
                    key("module","main_module")
                }
                crashlytics.log("Crash detected: $city")

                throw RuntimeException("Test Crash")

            }
        )

    }
}