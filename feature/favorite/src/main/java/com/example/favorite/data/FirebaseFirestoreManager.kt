package com.example.favorite.data

import com.google.firebase.firestore.FirebaseFirestore
import com.necdetzr.home.component.domain.data.Coin
import com.necdetzr.home.component.domain.data.DetailCoin
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseFirestoreManager(
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

) {

    suspend fun addToFavorite(userId: String, coin: DetailCoin?) {
        if (coin != null) {
            firestore.collection("users")
                .document(userId)
                .collection("favorites")
                .document(coin.id)
                .set(coin)
                .await()
        }

    }

    suspend fun removeFromFavorite(userId: String, coinId: String) {
        firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .document(coinId)
            .delete()
            .await()
    }

    suspend fun isFavorite(userId: String, coinId: String): Boolean {
        val snapshot = firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .document(coinId)
            .get()
            .await()
        return snapshot.exists()
    }

    fun getFavorites(userId: String): Flow<List<Coin>> = callbackFlow {
        val ref = firestore.collection("users")
            .document(userId)
            .collection("favorites")

        val listener = ref.addSnapshotListener { snapshot, _ ->
            val list = snapshot?.toObjects(Coin::class.java) ?: emptyList()
            trySend(list)
        }

        awaitClose { listener.remove() }
    }
}