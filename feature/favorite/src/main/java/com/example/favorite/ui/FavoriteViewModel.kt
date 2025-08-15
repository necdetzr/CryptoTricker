package com.example.favorite.ui

import androidx.lifecycle.viewModelScope
import com.example.favorite.data.FirebaseFirestoreManager
import com.example.network.manager.FirebaseAuthManager
import com.necdetzr.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val firestoreManager: FirebaseFirestoreManager,
    private val firebaseAuthManager: FirebaseAuthManager

) : BaseViewModel<FavoriteViewState>() {
    override fun createInitialState(): FavoriteViewState = FavoriteViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onEvent(event: FavoriteEvent){
        when(event){
            is FavoriteEvent.OnLoadFavorites -> loadFavorites(event.userId)
        }
    }

    init {
        loadUser()



    }

     fun loadFavorites(userId:String){

         viewModelScope.launch {
            showLoading(true)
            try {
                firestoreManager.getFavorites(userId).collect { list->
                    setState { copy(favoriteCoins = list) }
                }

            }catch (e: Exception){
                Timber.e("Error: $e")
            }
        }
    }
    private fun loadUser(){
        viewModelScope.launch {
            val userId = firebaseAuthManager.getCurrentUser()?.uid
            setState { copy(userId = userId) }
            try {
                userId?.let {
                    loadFavorites(it)
                }
            }catch (e: Exception){
                Timber.e("Erur : $e")
            }

        }
    }


}