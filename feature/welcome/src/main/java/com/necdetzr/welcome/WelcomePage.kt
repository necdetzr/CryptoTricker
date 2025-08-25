package com.necdetzr.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.necdetzr.designsystem.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui.component.AuthButton

@Composable
fun WelcomePage(
    onNavigateToRegister:()->Unit,
    onNavigateToLogin:()->Unit,
){


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            modifier = Modifier

                .fillMaxSize()
                .padding(16.dp)



        ) {

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(160.dp)
                    .clip(RoundedCornerShape(36.dp))
            )
            Spacer(Modifier.height(16.dp))
            Text(text = stringResource(id = com.necdetzr.designsystem.R.string.welcome_loodos_h1), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(com.necdetzr.designsystem.R.string.welcome_loodos_h2),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(60.dp))

            AuthButton(
                text = stringResource(id = com.necdetzr.designsystem.R.string.signup),
                onClick = {

                    onNavigateToRegister()

                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(stringResource(id = com.necdetzr.designsystem.R.string.or), style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(20.dp))
            AuthButton(
                onClick = {onNavigateToLogin() },
                text = stringResource(id = com.necdetzr.designsystem.R.string.sign_in),

                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.primaryContainer,

                )


        }



    }
