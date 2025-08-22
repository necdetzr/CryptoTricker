package com.necdetzr.settings.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ProfileSettingsScreen(
    viewModel: ProfileSettingsViewModel = hiltViewModel(),
    onNavigateToBack:()->Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    ProfileSettingsPage(
        name = uiState.name,
        email = uiState.email,
        onBackButton = {
            onNavigateToBack()
        },
        newName =uiState.newName,
        onNewNameChange = {viewModel.onEvent(ProfileSettingsEvent.ChangeName(it))},
        isLoading = uiState.loading,
        onNewNameSet = {viewModel.onEvent(ProfileSettingsEvent.SetNewName(uiState.newName))},
        count = uiState.count


    )
}