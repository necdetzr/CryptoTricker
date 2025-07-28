package com.example.profile.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.necdetzr.designsystem.R

@Composable
fun ProfileCard(
    name:String,
    surname:String,
    email:String
){
    Card(
        modifier = Modifier.fillMaxWidth().height(180.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                stringResource(R.string.name),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                name,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(20.dp))
            Text(
                stringResource(R.string.surname),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                surname,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(20.dp))
            Text(
                stringResource(R.string.email),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                email,
                style = MaterialTheme.typography.bodyLarge
            )

        }

    }
}


@Preview
@Composable
fun ProfileCardPreview(){
    ProfileCard(
        name = "Example Name",
        surname = "Example Surname",
        email = "Example Email"
    )
}