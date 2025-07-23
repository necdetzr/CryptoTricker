package com.example.settings.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos


import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.settings.data.SettingsItem


@Composable
fun SettingsCard(
    settings:List<SettingsItem>
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,

        ),

        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)



    ){
        LazyColumn {
            items(settings){setting->
                SettingsSection(
                    leadingIcon = setting.leadingIcon,
                    trailingIcon = setting.trailingIcon,
                    title = setting.title,
                    onClick = setting.onClick,
                    switch = setting.switch,
                    onCheckedChange = setting.onSwitchedChange,
                    checked = setting.checked
                )
                if(setting == settings.last()){
                Spacer(Modifier.height(0.dp))
                }else{
                    HorizontalDivider()
                }
            }
        }

    }
}



















@Preview(showBackground = false)
@Composable
fun SettingsCardPreview() {
    val dummySettings = listOf(
        SettingsItem(
            title = "Bildirim İzni",
            leadingIcon = Icons.Outlined.Notifications,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
            onClick = {}
        ),
        SettingsItem(
            title = "Gizlilik",
            leadingIcon = Icons.Outlined.PrivacyTip,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForwardIos,
            onClick = {}
        )
    )

    Surface {
        SettingsCard(settings = dummySettings)
    }
}

