package com.example.ui.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    value:String,
    placeholder:String,
    onValueChange:(String) -> Unit,
    enabled:Boolean = true,
){
    OutlinedTextField(
        value =  value,
        placeholder = {Text(placeholder)},
        enabled = enabled,
        onValueChange = onValueChange,
        leadingIcon = {Icon(imageVector = icon, contentDescription = "icon")},
        singleLine = true,
        shape = RoundedCornerShape(12.dp),

        colors = OutlinedTextFieldDefaults.colors(
           focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant





        ),
        modifier = modifier.fillMaxWidth()

    )
}