package com.necdetzr.language.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.necdetzr.language.data.LanguageItem


@Composable
fun LanguageCard(
    languages: List<LanguageItem>
){
    Card(
    colors = CardDefaults.cardColors(
    containerColor = MaterialTheme.colorScheme.surface,

    ),

    shape = RoundedCornerShape(12.dp),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)



    ){
        LazyColumn {
            items(languages){lang->
                LanguageSection(
                    selected = lang.checked,
                    title = lang.title,
                    onClick = lang.onClick
                )
                if(lang == languages.last()){
                    Spacer(Modifier.height(0.dp))
                }else{
                    HorizontalDivider()
                }
            }
        }

    }
}

@Preview(showBackground = false)
@Composable
fun LanguageCardPreview() {
    val dummySettings = listOf(
        LanguageItem(
            title = "Türkçe",
            checked = true,
            onClick = {}
        ),
        LanguageItem(
            title = "English",
            checked = false,
            onClick = {}
        )
    )

    Surface {
        LanguageCard(languages = dummySettings)
    }
}