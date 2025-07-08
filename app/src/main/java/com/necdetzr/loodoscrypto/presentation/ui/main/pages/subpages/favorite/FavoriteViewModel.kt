package com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import com.necdetzr.loodoscrypto.data.local.FirebaseFirestoreManager
import com.necdetzr.loodoscrypto.domain.model.DetailCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
   private val  firestoreManager: FirebaseFirestoreManager,
    private val authManager: FirebaseAuthManager
): ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState

    fun loadFavorites(userId: String) {
        viewModelScope.launch {
            firestoreManager.getFavorites(userId).collect {coinList->
                _uiState.update { it.copy(favorites = coinList) }
            }
        }
    }

    init {
        getUserId()

    }

    fun getUserId() {
        viewModelScope.launch {
            val userId = authManager.getCurrentUserId()
            _uiState.update { it.copy(userId = userId) }
        }

    }
}