package com.necdetzr.loodoscrypto.presentation.ui.auth.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.necdetzr.loodoscrypto.R
import com.necdetzr.loodoscrypto.presentation.theme.Blue
import com.necdetzr.loodoscrypto.presentation.theme.DarkBlue


@Composable
fun LoginPage(){
    Scaffold { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(R.drawable.logo2_removebg_preview), contentScale = ContentScale.Fit, contentDescription = "Logo", modifier = Modifier.size(140.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.sign_in_your_account_h1), style = MaterialTheme.typography.headlineLarge, color = DarkBlue)
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.sign_in_your_account_h2), style = MaterialTheme.typography.bodyMedium, color = Blue)
            Spacer(modifier = Modifier.height(10.dp))



        }
    }

}