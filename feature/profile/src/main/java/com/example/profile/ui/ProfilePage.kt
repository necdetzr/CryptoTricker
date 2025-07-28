package com.example.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profile.ui.components.ProfileCard
import com.example.profile.ui.components.SettingsCard
import com.example.ui.component.CryptoAppBar
import com.necdetzr.designsystem.R


@Composable
fun ProfilePage(
    name:String,
    surname:String,
    email:String,
    onSettingsButton:()-> Unit,
){

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding->
        CryptoAppBar(
            isLeadingButton = false,
            title = stringResource(R.string.profile),

        )

        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize()

        ) {

            Spacer(Modifier.height(42.dp))
            ProfileCard(
                name = name,
                surname = surname,
                email = email
            )
            Spacer(Modifier.height(12.dp))
            Text(
                stringResource(R.string.general_settings),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(2.dp))
            SettingsCard(
                onClick = onSettingsButton,
                title = stringResource(R.string.settings),
                leadingIcon = Icons.Default.Settings
            )


        }
    }
}

