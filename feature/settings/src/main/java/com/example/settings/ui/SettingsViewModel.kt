package com.necdetzr.settings.ui

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.example.analytics.AnalyticsHelper
import com.example.settings.ui.SettingsEvent
import com.example.settings.ui.SettingsViewState
import com.example.settings.util.OptionsFunctions.restartAppWithLocale
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.datastore.model.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class  SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val analyticsHelper: AnalyticsHelper

): BaseViewModel<SettingsViewState>(){


    override fun createInitialState(): SettingsViewState = SettingsViewState()
    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }
    init {
        getDarkMode()
    }
    fun setDarkMode(){
       // _uiState.update { it.copy(darkModeChecked = !it.darkModeChecked) }
        analyticsHelper.logEvent("dark_mode_changed")
        viewModelScope.launch {
            val newValue = !uiState.value.darkModeChecked
            setState { copy(darkModeChecked = newValue) }
            dataStoreManager.setDarkMode(newValue)

        }
    }

    private fun getDarkMode(){
        viewModelScope.launch {
            dataStoreManager.darkMode.collect { darkMode->
                setState { copy(darkModeChecked = darkMode) }
            }
        }
    }

    fun expandLanguage(expanded:Boolean){
        //_uiState.update { it.copy(expanded = expanded) }
        setState { copy(expanded = expanded) }
        analyticsHelper.logEvent("language_expanded")
    }
    fun showDialog(dialogShowed:Boolean){
       // _uiState.update { it.copy(showDialog = dialogShowed) }
        setState { copy(showDialog = dialogShowed) }
    }
    fun changeLang(language: String, context: android.content.Context){
        viewModelScope.launch {
            dataStoreManager.setLanguage(language)
            analyticsHelper.logEvent("language_change")
            val activity = context as? Activity ?: return@launch
            restartAppWithLocale(activity,language)

        }
    }

    fun logOut(onComplete: () -> Unit) {
        onComplete()
        analyticsHelper.logEvent("log_out")
    }

    fun onEvent(event: SettingsEvent){
        when(event){
            is SettingsEvent.SetDarkMode -> setDarkMode()
            is SettingsEvent.ShowDialog -> showDialog(event.isDialogShowed)
            is SettingsEvent.ChangeLanguage -> changeLang(event.language,event.context)
            is SettingsEvent.ExpandLanguage -> expandLanguage(event.isExpanded)
        }
    }


}