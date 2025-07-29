package com.example.login.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPasswordTextField(

    value:String,
    placeholder:String,
    onValueChange:(String) -> Unit,
){
    var visible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value =  value,
        placeholder = {Text(placeholder)},
        onValueChange = onValueChange,
        visualTransformation = if(visible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(Icons.Default.Password, contentDescription = "icon")
        },
        trailingIcon = {
            val icon = if(visible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            IconButton(onClick = {visible = !visible}) {
                Icon(imageVector = icon, contentDescription = "icon", tint = MaterialTheme.colorScheme.outlineVariant)
            }

        },

        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.outline,



            ),
        modifier = Modifier.fillMaxWidth()

    )
}