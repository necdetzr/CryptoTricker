package com.necdetzr.loodoscrypto.presentation.ui.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AuthButton(
    text:String,
    onClick : () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier

    ){
    Button(
       onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,


        ),
        shape = RoundedCornerShape(36.dp),
        modifier = modifier.size(width = 200.dp, height = 72.dp)


    ) {
        Text(text = text, style = MaterialTheme.typography.headlineSmall)

    }
}