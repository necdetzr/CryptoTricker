package com.necdetzr.settings.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.settings.ui.SettingsEvent
import com.example.settings.ui.SettingsPage

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateToLogin:()-> Unit,
    onNavigateToBack:()->Unit,

){
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()
    
    SettingsPage(
        expanded = uiState.expanded,
        onExpandedChange = {expanded->
            viewModel.onEvent(SettingsEvent.ExpandLanguage(expanded))
                           },
        darkModeChecked = uiState.darkModeChecked,
        onDarkModeToggle = {viewModel.onEvent(SettingsEvent.SetDarkMode)},
        onLanguageChange = { lang-> viewModel.onEvent(SettingsEvent.ChangeLanguage(lang,context)) },
        onLogOutClick ={viewModel.onEvent(SettingsEvent.ShowDialog(true))},
        showAlertDialog = uiState.showDialog,
        onDismissDialog = {viewModel.onEvent(SettingsEvent.ShowDialog(false))},
        onConfirmLogout = {viewModel.logOut {
            viewModel.showDialog(false)
            onNavigateToLogin()
        }},
        onBackButton = onNavigateToBack

    )

}