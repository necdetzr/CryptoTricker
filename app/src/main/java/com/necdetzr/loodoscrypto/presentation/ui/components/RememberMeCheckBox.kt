package com.necdetzr.loodoscrypto.presentation.ui.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.necdetzr.loodoscrypto.R


@Composable
fun RememberMeCheckBox(
    checked:Boolean,
    onChecked:() -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            onCheckedChange = {onChecked()},
            checked = checked,



        )
        Text(text = "Privacy Policy", style = MaterialTheme.typography.bodySmall)

    }

}