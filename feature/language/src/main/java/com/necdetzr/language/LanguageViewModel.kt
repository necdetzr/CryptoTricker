package com.necdetzr.language

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import com.necdetzr.common.base.BaseEvent
import com.necdetzr.common.base.BaseViewModel
import com.necdetzr.datastore.model.DataStoreManager
import com.necdetzr.language.util.OptionsFunctions.restartAppWithLocale
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,

): BaseViewModel<LanguageViewState>() {
    override fun createInitialState(): LanguageViewState = LanguageViewState()
    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }
    fun onEvent(viewEvent: LanguageEvent){
        when(viewEvent){
            is LanguageEvent.OnSetLanguage -> applySelectedLanguage( context = viewEvent.context)
            is LanguageEvent.OnUpdateLanguage -> updateSelectedLanguage(viewEvent.lang)
        }

    }

    init {
        viewModelScope.launch {
            val currentLang = dataStoreManager.languageFlow.first()
            setState { copy(lang = currentLang ?: "tr") }
        }
    }

    fun applySelectedLanguage(context: Context) {
        viewModelScope.launch {
            val language = uiState.value.lang
            dataStoreManager.setLanguage(language)
            val activity = context as? Activity ?: return@launch
            restartAppWithLocale(activity, language)
        }
    }

    fun updateSelectedLanguage(language: String) {
        setState { copy(lang = language)}
    }








}

sealed interface LanguageEvent: BaseEvent{
    data class OnSetLanguage(val context: Context) : LanguageEvent

    data class OnUpdateLanguage(val lang:String) : LanguageEvent
}