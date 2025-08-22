package com.necdetzr.settings.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.component.CryptoAppBar
import com.example.ui.component.CustomTextField
import com.example.ui.component.LinearProgressBar


@Composable
fun ProfileSettingsPage(
    name:String?,
    newName:String?,
    email:String?,
    onBackButton:()->Unit,
    onNewNameChange:(String)->Unit,
    onNewNameSet:()->Unit,
    isLoading:Boolean,
    count:Int
){
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CryptoAppBar(
                isLeadingButton = true,
                title = "Profile Settings",
                onClick = {
                    onBackButton()
                }
            )
        }
    ) {contentPadding->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(24.dp)
        ) {
            Text(
                "Username",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 16.sp)
            )
            Spacer(Modifier.height(4.dp))
            CustomTextField(
                icon = Icons.Default.Person,
                placeholder = name ?:"Guest User",
                value = newName ?: "",
                onValueChange = onNewNameChange

            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                var color = MaterialTheme.colorScheme.primary
                if(count>=17) color = MaterialTheme.colorScheme.error
                Text("$count/20",color = color)
            }
            Spacer(Modifier.height(6.dp))
            Text(
                "E-Mail",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 16.sp)
            )
            Spacer(Modifier.height(4.dp))
            CustomTextField(
                icon = Icons.Default.Mail,
                placeholder = email ?: "Guest User",
                value = email ?: "Guest User",
                onValueChange = {},
                enabled = false
            )
            Spacer(Modifier.weight(1f))
            if(isLoading){
                LinearProgressBar()
            }else{
                Button(
                    onClick = {onNewNameSet()},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()

                ){
                    Text("Save Changes", modifier = Modifier.padding(6.dp))
                }
            }


        }


    }
}

@Preview
@Composable
fun ProfileSettingsPagePreview(){
    ProfileSettingsPage(
        name = "Name",
        email = "test1@example.com",
        newName = "",
        onBackButton = {},
        onNewNameChange = {},
        isLoading = false,
        onNewNameSet = {},
        count = 0
    )
}