package com.example.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.DoorBack
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.settings.ui.components.SettingsCard
import kotlinx.coroutines.launch

import com.example.settings.ui.components.WarningPopUp



@Composable
fun SettingsPage(
   expanded: Boolean,
   onExpandedChange: (Boolean) -> Unit,
   darkModeChecked: Boolean,
   onDarkModeToggle: suspend () -> Unit,
   onLanguageChange: suspend (String) -> Unit,
   onLogOutClick: () -> Unit,
   showAlertDialog: Boolean,
   onDismissDialog: () -> Unit,
   onConfirmLogout: () -> Unit,
   onBackButton:()->Unit,
) {
   val coroutineScope = rememberCoroutineScope()

   Column(
      modifier = Modifier.padding(24.dp)
   ) {
      Row(
         horizontalArrangement = Arrangement.Center,
         verticalAlignment = Alignment.CenterVertically
      ) {
         IconButton(
            onClick = onBackButton
         ) {
            Icon(Icons.Default.ArrowBackIos, contentDescription = "back")
         }
         Text("Settings", style = MaterialTheme.typography.headlineSmall)

      }
      Spacer(Modifier.height(24.dp))
      SettingsCard(
         titleIcon = Icons.Rounded.Language,
         title = stringResource(R.string.language_settings),
         onClick = { onExpandedChange(true) },
         buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight
      )

      DropdownMenu(
         expanded = expanded,
         onDismissRequest = { onExpandedChange(false) },
         offset = DpOffset(x = 260.dp, y = (-160).dp)
      ) {
         DropdownMenuItem(
            text = { Text("English") },
            onClick = {
               onExpandedChange(false)
               coroutineScope.launch {
                  onLanguageChange("en")
               }
            }
         )
         DropdownMenuItem(
            text = { Text("Türkçe") },
            onClick = {
               onExpandedChange(false)
               coroutineScope.launch {
                  onLanguageChange("tr")
               }
            }
         )
      }

      Spacer(Modifier.height(12.dp))

      SettingsCard(
         titleIcon = Icons.Rounded.DarkMode,
         title = stringResource(R.string.dark_mode),
         onClick = {},
         buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight,
         iconColor = MaterialTheme.colorScheme.primary,
         textColor = MaterialTheme.colorScheme.onPrimaryContainer,
         switch = true,
         onCheckedChange = {
            coroutineScope.launch {
               onDarkModeToggle()
            }
         },
         checked = darkModeChecked
      )

      Spacer(Modifier.height(12.dp))

      SettingsCard(
         titleIcon = Icons.Rounded.DoorBack,
         title = stringResource(R.string.log_out),
         onClick = onLogOutClick,
         buttonIcon = Icons.AutoMirrored.Rounded.ArrowRight,
         iconColor = MaterialTheme.colorScheme.error,
         textColor = MaterialTheme.colorScheme.error
      )

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
   }
}
