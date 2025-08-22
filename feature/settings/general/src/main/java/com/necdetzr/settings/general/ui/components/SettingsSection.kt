package com.necdetzr.settings.general.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSection(
    leadingIcon: ImageVector,
    trailingIcon:ImageVector,
    title:String,
    onClick:()->Unit,
    switch:Boolean = false,
    checked:Boolean = false,
    onCheckedChange: ()-> Unit = {}

){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(20.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {onClick()}
            )

    ) {
        Box(
            modifier = Modifier.size(36.dp).clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(0.1f)),

            contentAlignment = Alignment.Center
        ){

            Icon(
                imageVector = leadingIcon,
                contentDescription = "leadingIcon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))
        Text(title, style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Medium
        ))
        Spacer(Modifier.weight(1f))
        if (switch) {
            Switch(checked = checked, onCheckedChange = { onCheckedChange() })
        } else {
            Icon(
                imageVector = trailingIcon,
                contentDescription = "trailingIcon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(20.dp)
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SettingsSectionPreview() {
    SettingsSection(
        leadingIcon = Icons.Filled.Settings,
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
        title = "Settings",
        onClick = {}
    )
    Spacer(Modifier.height(40.dp))
    SettingsSection(
        leadingIcon = Icons.Filled.Settings,
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
        title = "Settings",
        onClick = {},
        onCheckedChange = {},
        switch = true
    )

}