package com.necdetzr.loodoscrypto.presentation.ui.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SettingsCard(
    titleIcon: ImageVector,
    title:String,
    onClick:() -> Unit,
    buttonIcon:ImageVector,
    iconColor:Color = Color.Black,
    textColor:Color = Color.Black
){
    Card(
        border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier.fillMaxWidth().clickable(
            onClick = onClick
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(titleIcon, contentDescription = "$title Icon", tint = iconColor)
            Spacer(Modifier.width(12.dp))
            Text(title, style = MaterialTheme.typography.bodyMedium, color = textColor)
            Spacer(Modifier.weight(1f))
            Icon(buttonIcon, "$title Icon Button")



        }

    }
}