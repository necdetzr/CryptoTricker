package com.necdetzr.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.necdetzr.language.components.LanguageCard
import com.necdetzr.language.data.LanguageItem


@Composable
fun LanguagePage(
    uiState: LanguageViewState,
    onViewEvent:(LanguageEvent) -> Unit,
    navigateBack:()->Unit
){
    val context = LocalContext.current
    val languageList = listOf(
        LanguageItem(
            title = "Türkçe",
            checked =uiState.lang == AppLanguage.TURKISH.code,
            onClick = {onViewEvent(LanguageEvent.OnUpdateLanguage(AppLanguage.TURKISH.code))}

        ),
        LanguageItem(
            title = "English",
            checked =uiState.lang == AppLanguage.ENGLISH.code,
            onClick = {onViewEvent(LanguageEvent.OnUpdateLanguage(AppLanguage.ENGLISH.code))}

        ),

    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape
                    )
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Geri",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Text(
                text = stringResource(com.necdetzr.designsystem.R.string.language_settings),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
            LanguageCard(languages = languageList)
            Spacer(Modifier.weight(1f))

            Button(
                onClick = {onViewEvent(LanguageEvent.OnSetLanguage(context))},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)

            ) { Text("Uygula")}
        }


    }
}

enum class AppLanguage(val code: String, val displayName: String) {
    TURKISH("tr", "Türkçe"),
    ENGLISH("en", "English")
}
