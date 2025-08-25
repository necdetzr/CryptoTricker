package com.example.network.di

import com.example.network.manager.FirebaseAuthManager
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    @Singleton
    @Provides
    fun providesFirebaseAuthManager(auth:FirebaseAuth): FirebaseAuthManager {
        return FirebaseAuthManager(auth)
    }

}