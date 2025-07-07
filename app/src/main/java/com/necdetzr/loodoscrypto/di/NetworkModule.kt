package com.necdetzr.loodoscrypto.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.necdetzr.loodoscrypto.data.api.CoinApi
import com.necdetzr.loodoscrypto.data.datastore.DataStoreManager
import com.necdetzr.loodoscrypto.data.local.FirebaseAuthManager
import com.necdetzr.loodoscrypto.data.local.FirebaseFirestoreManager
import com.necdetzr.loodoscrypto.data.local.FirebaseRemoteConfigManager
import com.necdetzr.loodoscrypto.utils.NetworkConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager{
        return DataStoreManager(context)
    }
    @Singleton
    @Provides
    fun provideCoinApi(retrofit: Retrofit): CoinApi {
        return retrofit.create(CoinApi::class.java)
    }
    @Provides
    @Singleton
    fun provideFirebaseFirestoreManager(): FirebaseFirestoreManager {
        return FirebaseFirestoreManager()
    }


    @Provides
    @Singleton
    fun provideFirebaseRemoteConfigManager(firebaseRemoteConfig:FirebaseRemoteConfig) : FirebaseRemoteConfigManager{
        return FirebaseRemoteConfigManager(firebaseRemoteConfig)
    }
    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig():FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    @Singleton
    @Provides
    fun providesFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()
    @Singleton
    @Provides
    fun providesFirebaseAuthManager(auth:FirebaseAuth):FirebaseAuthManager {
        return FirebaseAuthManager(auth)
    }

}