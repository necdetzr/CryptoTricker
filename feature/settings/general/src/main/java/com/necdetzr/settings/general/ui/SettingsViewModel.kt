package com.necdetzr.settings.general.ui

import androidx.lifecycle.viewModelScope
import com.example.analytics.AnalyticsHelper
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

    fun showDialog(dialogShowed:Boolean){
       // _uiState.update { it.copy(showDialog = dialogShowed) }
        setState { copy(showDialog = dialogShowed) }
    }


    fun logOut(onComplete: () -> Unit) {
        onComplete()
        analyticsHelper.logEvent("log_out")
    }

    fun onEvent(event: SettingsEvent){
        when(event){
            is SettingsEvent.SetDarkMode -> setDarkMode()
            is SettingsEvent.ShowDialog -> showDialog(event.isDialogShowed)
        }
    }


}