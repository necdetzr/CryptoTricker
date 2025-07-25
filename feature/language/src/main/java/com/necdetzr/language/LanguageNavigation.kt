package com.necdetzr.language

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.language(
    navigateBack:()->Unit,
) {
    composable("language"){
        LanguageScreen(
            navigateBack
        )
    }
}