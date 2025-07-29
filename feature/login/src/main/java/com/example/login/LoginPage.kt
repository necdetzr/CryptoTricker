package com.example.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.login.components.AuthPasswordTextField
import com.example.ui.component.AuthButton
import com.example.ui.component.CustomTextField
import com.example.ui.component.LinearProgressBar
import com.example.ui.component.RememberMe
import com.necdetzr.designsystem.R

@Composable
fun LoginPage(
    isLoading:Boolean,
    onNavigateToRegister:()->Unit,
    onLoginClick:()->Unit,
    checked:Boolean,
    onCheckedChange:(Boolean)->Unit

){
    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 30.dp, horizontal = 30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                stringResource(R.string.sign_in_your_account_h1),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                stringResource(R.string.sign_in_your_account_h2),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                icon = Icons.Outlined.Email,
                value = email,
                placeholder = stringResource(R.string.email),
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            AuthPasswordTextField(
                password,
                stringResource(R.string.password),
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RememberMe(
                    checked = checked,
                    onCheckedChange = onCheckedChange
                )
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            if (isLoading) {
                Spacer(Modifier.height(32.dp))
                LinearProgressBar()
            } else {
                AuthButton(
                    text = stringResource(R.string.sign_in),
                    onClick =onLoginClick,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(Modifier.weight(1f))

            Row {
                Text(
                    stringResource(R.string.dont_have),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    stringResource(R.string.signup),
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable { onNavigateToRegister() }
                )
            }

            SnackbarHost(hostState = snackbarHostState)
        }
    }
}