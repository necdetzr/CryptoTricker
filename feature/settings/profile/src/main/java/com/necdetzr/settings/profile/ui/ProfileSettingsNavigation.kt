package com.necdetzr.settings.profile.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.profileSettings(
    onNavigateToBack:()->Unit
){
    composable("profileSettings"){
        ProfileSettingsScreen(
            onNavigateToBack = onNavigateToBack
        )
    }
}