package com.necdetzr.loodoscrypto.presentation.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.necdetzr.loodoscrypto.presentation.theme.DarkBlue
import com.necdetzr.loodoscrypto.presentation.theme.Gray

@Composable
fun ProfileCard(name:String,email:String){

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp).height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkBlue
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.Rounded.Person, contentDescription = "Profile Icon", tint = Color.White)
            Spacer(Modifier.width(10.dp))
            Column(
                modifier =Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(name, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                Spacer(Modifier.height(4.dp))
                Text(email, style = MaterialTheme.typography.bodySmall, color = Gray)


            }
            Spacer(Modifier.weight(1f))

        }
    }

}