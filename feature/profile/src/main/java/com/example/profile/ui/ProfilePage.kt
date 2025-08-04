package com.example.profile.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.profile.ui.components.ProfileCard
import com.example.profile.ui.components.SettingsCard

import com.example.ui.component.CryptoAppBar
import com.necdetzr.designsystem.R

@Composable
fun ProfilePage(
    name: String,
    surname: String,
    email: String,
    onSettingsButton: () -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CryptoAppBar(
                isLeadingButton = false,
                title = stringResource(R.string.profile),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize()
        ) {
            ProfileCard(
                name = name,
                surname = surname,
                email = email
            )

            Spacer(Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.general_settings),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(Modifier.height(16.dp))

            SettingsCard(
                onClick = onSettingsButton,
                title = stringResource(R.string.settings),
                leadingIcon = Icons.Outlined.Settings
            )
        }
    }
}
