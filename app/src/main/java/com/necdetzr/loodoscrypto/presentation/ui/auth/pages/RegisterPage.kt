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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.presentation.theme.Blue
import com.necdetzr.loodoscrypto.presentation.theme.DarkBlue
import com.necdetzr.loodoscrypto.presentation.ui.components.AuthButton
import com.necdetzr.loodoscrypto.presentation.ui.components.AuthPasswordTextField
import com.necdetzr.loodoscrypto.presentation.ui.components.CustomTextField
import com.necdetzr.loodoscrypto.presentation.ui.components.RememberMeCheckBox


@Composable
fun RegisterPage(
    navController: NavHostController
){
    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var password1 by remember { mutableStateOf("")}
    var checked by remember {mutableStateOf(false)}
    var name by remember {mutableStateOf("")}
    var surname by remember {mutableStateOf("")}

    Scaffold { innerPadding->

        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(vertical = 30.dp, horizontal = 30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(R.drawable.logo2_removebg_preview), contentScale = ContentScale.Fit, contentDescription = "Logo", modifier = Modifier.size(140.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.create_an_account_h1), style = MaterialTheme.typography.headlineLarge, color = DarkBlue)
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.create_an_account_h2), style = MaterialTheme.typography.bodyMedium, color = Blue)
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                Icons.Outlined.Person,
                name,
                stringResource(R.string.name),
                {name = it}

            )
            Spacer(Modifier.height(20.dp))
            CustomTextField(
                Icons.Outlined.Person,
                surname,
                stringResource(R.string.surname),
                {surname = it}

            )
            Spacer(Modifier.height(20.dp))

            CustomTextField(
                Icons.Outlined.Email,
                email,
                stringResource(R.string.email),
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()

            ) {
                RememberMeCheckBox(
                    checked = checked,
                    onChecked  = {checked = !checked}
                )
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.bodySmall,
                    color = Blue
                )

            }
            Spacer(modifier = Modifier.height(40.dp))
            Row {
                Text(stringResource(R.string.have_account), style = MaterialTheme.typography.bodySmall, color = Blue)
                Spacer(Modifier.width(4.dp))
                Text(stringResource(R.string.sign_in), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = DarkBlue, modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("register") {
                                inclusive = true
                                saveState = true
                            }
                        }
                    }

                ))
            }
            Spacer(Modifier.height(4.dp))
            AuthButton(
                text = stringResource(R.string.signup),
                onClick = { /*TODO*/ },
                containerColor = DarkBlue,
                contentColor = Color.White
            )


        }
    }

}