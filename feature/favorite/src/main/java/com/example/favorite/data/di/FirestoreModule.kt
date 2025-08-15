package com.example.favorite.data.di

import com.example.favorite.data.FirebaseFirestoreManager
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FirestoreModule {

    @Provides
    @Singleton
    fun providesFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesFirestoreManager(fireStore: FirebaseFirestore): FirebaseFirestoreManager {
        return FirebaseFirestoreManager(fireStore)
    }
}