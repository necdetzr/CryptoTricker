package com.example.settings.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.analytics.AnalyticsHelper
import com.necdetzr.datastore.model.DataStoreManager

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class  SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,

) : ViewModel(){

    private val _uiState = MutableStateFlow(SettingsViewState())
    val uiState : StateFlow<SettingsViewState> = _uiState


    fun setDarkMode(){
        _uiState.update { it.copy(darkModeChecked = !it.darkModeChecked) }
    }

    fun expandLanguage(expanded:Boolean){
        _uiState.update { it.copy(expanded = expanded) }
    }
    fun showDialog(dialogShowed:Boolean){
        _uiState.update { it.copy(showDialog = dialogShowed) }
    }
    fun changeLang(language: String, context: android.content.Context){
        viewModelScope.launch {
            dataStoreManager.setLanguage(language)
            val activity = context as? Activity ?: return@launch
            //restartAppWithLocale(activity,language)

        }
    }

    fun logOut(onComplete: () -> Unit) {
        onComplete()
    }


}