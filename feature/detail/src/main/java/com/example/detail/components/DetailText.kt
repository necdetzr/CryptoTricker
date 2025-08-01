package com.example.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailText(
    title:String,
    text:String
){
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(title, style = MaterialTheme.typography.bodySmall,color = MaterialTheme.colorScheme.onSurface)
        Text(text, style = MaterialTheme.typography.bodyMedium)


    }
}