package com.necdetzr.loodoscrypto.presentation.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.necdetzr.loodoscrypto.R

@Composable
fun ErrorCard(
    onClick: () -> Unit = {}
){
    Card(
        modifier = Modifier.size(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF700000),
            contentColor = Color.White
        )
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.Warning, tint = Color.Yellow, contentDescription = "warning")
            Spacer(Modifier.height(10.dp))
            Text(stringResource(R.string.error),style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,color = Color.White)
            Spacer(Modifier.height(10.dp))
            Text(stringResource(R.string.too_many_request),style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,color = Color.White)
            Text(stringResource(R.string.i_dont_have_money),style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,color = Color.White, textAlign = TextAlign.Center)
            Spacer(Modifier.height(6.dp))
            IconButton(onClick = {onClick()}) {
                Icon(Icons.Default.Refresh, contentDescription = "refresh")
            }
        }
    }


}