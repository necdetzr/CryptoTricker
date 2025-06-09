package com.necdetzr.loodoscrypto.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.necdetzr.loodoscrypto.presentation.theme.LightGray

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
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = LightGray,
            cursorColor = Color.Black



        ),
        modifier = modifier.fillMaxWidth()

    )
}