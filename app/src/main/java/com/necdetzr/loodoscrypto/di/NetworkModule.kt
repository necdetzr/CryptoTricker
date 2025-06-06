package com.necdetzr.loodoscrypto.di

import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.necdetzr.loodoscrypto.data.api.CoinApi
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import com.necdetzr.loodoscrypto.utils.NetworkConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Singleton
    @Provides
    fun provideCoinApi(retrofit: Retrofit): CoinApi {
        return retrofit.create(CoinApi::class.java)
    }
    @Singleton
    @Provides
    fun providesFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()
    @Singleton
    @Provides
    fun providesFirebaseAuthManager(auth:FirebaseAuth):FirebaseAuthManager {
        return FirebaseAuthManager(auth)
    }

}