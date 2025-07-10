package com.necdetzr.loodoscrypto.presentation.ui.auth.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.presentation.theme.Blue
import com.necdetzr.loodoscrypto.presentation.theme.DarkBlue
import com.necdetzr.loodoscrypto.presentation.ui.components.AuthButton


@Composable
fun WelcomePage(
    onNavigateToRegister:()-> Unit,
    onNavigateToLogin:()->Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier

                .padding(vertical = 80.dp, horizontal = 40.dp)
                .padding(innerPadding)
                .fillMaxSize()


        ) {

            Image(
                painter = painterResource(R.drawable.logo2_removebg_preview),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,

                )
            Text(text = stringResource(id = R.string.welcome_loodos_h1), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.welcome_loodos_h2),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(60.dp))

            AuthButton(
                text = stringResource(id = R.string.signup),
                onClick = {

                        onNavigateToRegister()

                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(stringResource(id = R.string.or), style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(20.dp))
            AuthButton(
                onClick = {onNavigateToLogin() },
                text = stringResource(id = R.string.sign_in),

                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.primaryContainer,

                )


        }



    }
}



