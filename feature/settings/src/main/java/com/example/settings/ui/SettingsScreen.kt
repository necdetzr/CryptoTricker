package com.example.settings.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

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
        onExpandedChange = {viewModel.expandLanguage(it)},
        darkModeChecked = uiState.darkModeChecked,
        onDarkModeToggle = {viewModel.setDarkMode()},
        onLanguageChange = { lang-> viewModel.changeLang(lang,context) },
        onLogOutClick ={viewModel.showDialog(true)},
        showAlertDialog = uiState.showDialog,
        onDismissDialog = {viewModel.showDialog(false)},
        onConfirmLogout = {viewModel.logOut {
            viewModel.showDialog(false)
            onNavigateToLogin()
        }},
        onBackButton = onNavigateToBack

    )

}