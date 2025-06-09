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
import kotlinx.coroutines.launch


@Composable
fun LoginPage(
    navController: NavHostController,
    viewModel: AuthViewModel
){
    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var isLoadings by remember{
        mutableStateOf(false)
    }
    var wasLoginAttempted by remember {mutableStateOf(false)}
    val loginState by viewModel.loginState.collectAsState()
    var loginError by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(loginState,wasLoginAttempted) {
        loginState?.let { result ->
            isLoadings = false
            result.onSuccess {
                loginError = null
                wasLoginAttempted = false
                navController.navigate("main"){
                    popUpTo("login"){
                        inclusive = true
                        saveState = true
                    }

                }
            }
            result.onFailure { error->
                loginError = error.message ?: "Invalid Email or Password"
                scope.launch {
                    snackbarHostState.showSnackbar("Invalid Email or Password")
                }

            }

        }
    }


    Scaffold(
        containerColor = Color.White
    ) { innerPadding->


        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(vertical = 30.dp, horizontal = 30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(R.drawable.logo2_removebg_preview), contentScale = ContentScale.Fit, contentDescription = "Logo", modifier = Modifier.size(140.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.sign_in_your_account_h1), style = MaterialTheme.typography.headlineLarge, color = DarkBlue)
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.sign_in_your_account_h2), style = MaterialTheme.typography.bodyMedium, color = Blue)
            Spacer(modifier = Modifier.height(20.dp))
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()

            ) {

                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.bodySmall,
                    color = Blue
                )

            }
            Spacer(modifier = Modifier.height(40.dp))
            Row {
                Text(stringResource(R.string.dont_have), style = MaterialTheme.typography.bodySmall, color = Blue)
                Spacer(Modifier.width(4.dp))
                Text(stringResource(R.string.signup), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = DarkBlue, modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate("register") {
                            popUpTo("login") {
                                inclusive = true
                                saveState = true
                            }
                        }
                    }
                ))
            }
            Spacer(Modifier.height(4.dp))
            if(isLoadings){
                Spacer(Modifier.height(32.dp))
                LinearProgressBar()

            }else{
                AuthButton(
                    text = stringResource(R.string.sign_in),
                    onClick = {
                        isLoadings = true
                        wasLoginAttempted = true
                        viewModel.login(email, password)

                    },
                    containerColor = Color.White,
                    contentColor = DarkBlue
                )
            }
            SnackbarHost(
                hostState = snackbarHostState)



        }
    }

}