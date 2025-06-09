package com.necdetzr.loodoscrypto.presentation.ui.main.pages.subpages.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import com.necdetzr.loodoscrypto.data.local.FirebaseFirestoreManager
import com.necdetzr.loodoscrypto.domain.model.DetailCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
   private val  firestoreManager: FirebaseFirestoreManager,
    private val authManager: FirebaseAuthManager
): ViewModel(){
    private val _favorites = MutableStateFlow<List<DetailCoin?>>(emptyList())
    val favorites: StateFlow<List<DetailCoin?>> = _favorites
    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId

    fun loadFavorites(userId:String){
        viewModelScope.launch {
             firestoreManager.getFavorites(userId).collect {
                 _favorites.value = it
             }
        }
    }
    init{
        getUserId()

    }
    fun getUserId(){
        viewModelScope.launch {
            _userId.value = authManager.getCurrentUserId()
        }
    }
    fun addFavorite(userId: String, coin: DetailCoin?){
        viewModelScope.launch {
            firestoreManager.addToFavorite(userId,coin)
        }
    }
    fun removeFavorite(userId: String, coinId: String){
        viewModelScope.launch {
            firestoreManager.removeFromFavorite(userId,coinId)
        }

    }
    suspend fun isFavorite(userId: String, coinId: String): Boolean {
        return firestoreManager.isFavorite(userId, coinId)
    }
}