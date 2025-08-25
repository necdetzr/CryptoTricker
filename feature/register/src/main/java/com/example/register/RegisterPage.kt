package com.example.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.component.AuthButton
import com.example.ui.component.AuthPasswordTextField
import com.example.ui.component.CustomTextField
import com.example.ui.component.LinearProgressBar
import com.necdetzr.designsystem.R

@Composable
fun RegisterPage(

    isLoadings:Boolean,
    onRegisterClick:()->Unit,
    onNavigateToLogin:()->Unit
){
    var name by remember { mutableStateOf("") }
    var surname by remember {mutableStateOf("")}
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding->


        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(vertical = 30.dp, horizontal = 30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
                    .clip(RoundedCornerShape(36.dp))
            )
            Spacer(Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.create_an_account_h1), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.create_an_account_h2), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                icon =Icons.Outlined.Person,
                value = name,
                placeholder = stringResource(R.string.name),
                onValueChange = {name = it}

            )
            Spacer(Modifier.height(20.dp))
            CustomTextField(
                icon = Icons.Outlined.Person,
                value = surname,
                placeholder = stringResource(R.string.surname),
                onValueChange = {surname = it}

            )
            Spacer(Modifier.height(20.dp))

            CustomTextField(
                icon = Icons.Outlined.Email,
                value = email,
                placeholder = stringResource(R.string.email),
                onValueChange =
                    {
                        email = it

                    }
            )
            Spacer(modifier = Modifier.height(20.dp))
            AuthPasswordTextField(

                password,
                stringResource(R.string.password),
                {
                    password = it
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Spacer(modifier = Modifier.height(40.dp))

            Spacer(Modifier.height(4.dp))

            if(isLoadings){
                LinearProgressBar()
            }else{
                AuthButton(
                    text = stringResource(R.string.signup),
                    onClick = {

                        onRegisterClick()
                    },
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.primaryContainer
                )
            }
            Spacer(Modifier.weight(1f))
            Row {
                Text(stringResource(R.string.have_account), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground)
                Spacer(Modifier.width(4.dp))
                Text(stringResource(R.string.sign_in), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.clickable(
                    onClick = {
                        onNavigateToLogin()
                    }

                ))
            }



        }
    }

}