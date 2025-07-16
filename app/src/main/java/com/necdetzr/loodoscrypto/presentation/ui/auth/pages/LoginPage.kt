package com.necdetzr.loodoscrypto.presentation.ui.auth.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.presentation.theme.Blue
import com.necdetzr.loodoscrypto.presentation.theme.DarkBlue
import com.necdetzr.loodoscrypto.presentation.ui.auth.AuthViewModel
import com.necdetzr.loodoscrypto.presentation.ui.components.AuthButton
import com.necdetzr.loodoscrypto.presentation.ui.components.AuthPasswordTextField
import com.necdetzr.loodoscrypto.presentation.ui.components.CustomTextField
import com.necdetzr.loodoscrypto.presentation.ui.components.LinearProgressBar
import com.necdetzr.loodoscrypto.presentation.ui.components.RememberMeCheckBox
import com.necdetzr.loodoscrypto.presentation.ui.main.components.RememberMe
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun LoginPage(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val rememberMe by viewModel.rememberMe.collectAsState(initial = false)
    var checked by remember { mutableStateOf(rememberMe) }

    var wasLoginAttempted by remember { mutableStateOf(false) }
    val loginState by viewModel.loginState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(loginState, wasLoginAttempted) {
        loginState?.let { result ->
            isLoading = false
            result.onSuccess {
                wasLoginAttempted = false
                viewModel.setRememberMe(checked)
                onNavigateToMain()
            }
            result.onFailure {
                scope.launch {
                    snackbarHostState.showSnackbar("Invalid Email or Password")
                }
            }
        }
    }

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
            // [Logo + Headings]
            Image(
                painter = painterResource(R.drawable.logo2_removebg_preview),
                contentScale = ContentScale.Fit,
                contentDescription = "Logo",
                modifier = Modifier.size(140.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
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

            // [Email & Password Fields]
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

            // [Remember Me & Forgot Password]
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                RememberMe(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // [Sign In Button or Loading]
            if (isLoading) {
                Spacer(Modifier.height(32.dp))
                LinearProgressBar()
            } else {
                AuthButton(
                    text = stringResource(R.string.sign_in),
                    onClick = {
                        isLoading = true
                        wasLoginAttempted = true
                        viewModel.login(email, password)
                    },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(Modifier.weight(1f))

            // [Sign Up Link]
            Row {
                Text(
                    stringResource(R.string.dont_have),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    stringResource(R.string.signup),
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable { onNavigateToLogin() }
                )
            }

            SnackbarHost(hostState = snackbarHostState)
        }
    }
}
