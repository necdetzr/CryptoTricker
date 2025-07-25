package com.necdetzr.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.monitor.AppEvent
import kotlinx.coroutines.selects.select

@Composable
fun LanguageScreen(
    navigateOnBack:()->Unit,
    viewModel: LanguageViewModel = hiltViewModel()
){
    
    val uiState by viewModel.uiState.collectAsState()
    
    LanguagePage(
       onViewEvent = viewModel::onEvent,
        uiState = uiState,
        navigateBack = navigateOnBack
    )



}