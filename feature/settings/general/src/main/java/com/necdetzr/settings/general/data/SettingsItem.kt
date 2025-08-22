package com.necdetzr.settings.general.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.graphics.vector.ImageVector

data class SettingsItem(
    val title: String,
    val leadingIcon: ImageVector,
    val trailingIcon: ImageVector = Icons.Default.ArrowForward,
    val onClick: () -> Unit,
    val switch : Boolean = false,
    val onSwitchedChange:()->Unit = {},
    val checked:Boolean = false
)
