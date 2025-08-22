package com.necdetzr.settings.general.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DoorBack
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.necdetzr.settings.general.data.SettingsItem
import com.necdetzr.settings.general.ui.components.FooterSection
import com.necdetzr.settings.general.ui.components.SettingsCard
import com.necdetzr.settings.general.ui.components.WarningPopUp
import com.necdetzr.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(
   onLanguageButton:()->Unit,
   darkModeChecked: Boolean,
   onDarkModeToggle: () -> Unit,
   onLogOutClick: () -> Unit,
   showAlertDialog: Boolean,
   onDismissDialog: () -> Unit,
   onConfirmLogout: () -> Unit,
   onBackButton: () -> Unit,
) {
   val settingsList =
      listOf(
         SettingsItem(
            title = stringResource(R.string.language_settings),
            leadingIcon = Icons.Rounded.Language,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
            onClick = { onLanguageButton()
                       }
         ),
         SettingsItem(
            title =stringResource(R.string.dark_mode),
            leadingIcon = Icons.Outlined.DarkMode,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
            onClick = { onDarkModeToggle()  },
            onSwitchedChange = { onDarkModeToggle() },
            switch = true,
            checked = darkModeChecked
         ),
         SettingsItem(
            title = stringResource(R.string.log_out),
            leadingIcon = Icons.Outlined.DoorBack,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
            onClick = onLogOutClick
         )
      )

   Column(modifier = Modifier.fillMaxSize()) {
      Box(
         modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.primary)
      ) {
         Box(
            modifier = Modifier
               .align(Alignment.CenterStart)
               .padding(start = 8.dp)
               .background(
                  color = MaterialTheme.colorScheme.onPrimary,
                  shape = CircleShape
               )
               .size(40.dp),
            contentAlignment = Alignment.Center
         ) {
            IconButton(onClick = onBackButton) {
               Icon(
                  imageVector = Icons.AutoMirrored.Default.ArrowBack,
                  contentDescription = "Geri",
                  tint = MaterialTheme.colorScheme.onPrimaryContainer
               )
            }
         }

         Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center)
         )
      }

      Column(
         modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
      ) {



         SettingsCard(settings = settingsList)



         if (showAlertDialog) {
            WarningPopUp(
               onAcceptRequest = onConfirmLogout,
               onDismissRequest = onDismissDialog,
               title = "Log Out?",
               text = "Are you sure to log out from application?",
               firstButton = "Cancel",
               secondButton = "Log Out"
            )
         }

         Spacer(Modifier.weight(1f))

         FooterSection(
            year = "2025",
            company = "Necdetzr",
            version = "0.0.1 alpha"
         )
      }
   }
}