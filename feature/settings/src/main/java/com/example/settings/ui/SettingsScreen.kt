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
    onNavigateToLanguage:()->Unit,

){

    val uiState by viewModel.uiState.collectAsState()
    
    SettingsPage(
        darkModeChecked = uiState.darkModeChecked,
        onDarkModeToggle = {viewModel.onEvent(SettingsEvent.SetDarkMode)},
        onLanguageButton = { onNavigateToLanguage() },
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