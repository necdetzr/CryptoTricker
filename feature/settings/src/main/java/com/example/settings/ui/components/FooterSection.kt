package com.example.settings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FooterSection(
    version:String,
    company:String,
    year:String
){
    Column {
        Text(
            "Sürüm $version",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(Modifier.height(10.dp))
        Text("Powered by $company",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(16.dp))
        Text("Copyright © $year",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodyMedium
        )
        Text("This is a $company Teknoloji",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodyMedium

        )
        Text("Tüm Hakları Saklıdır.",
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.bodyMedium

        )


    }
}

@Preview(showBackground = true)
@Composable
fun FooterSectionPreview(){
    FooterSection(
        version = "1.5.0",
        company = "Loodos",
        year = "2025"
    )
}