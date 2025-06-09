package com.necdetzr.loodoscrypto.presentation.ui.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.necdetzr.loodoscrypto.presentation.theme.Gray

@Composable
fun DetailText(
    title:String,
    text:String
){
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(title, style = MaterialTheme.typography.bodySmall,color = Gray)
        Text(text, style = MaterialTheme.typography.bodyMedium)


    }
}