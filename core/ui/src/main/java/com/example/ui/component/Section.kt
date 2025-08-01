package com.example.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun Section(
    firstTitle:String,
    lastTitle:String = "",

    ){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(firstTitle, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface, fontSize = 14.sp)
        Text(lastTitle, style = MaterialTheme.typography.headlineSmall,color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
    }
}