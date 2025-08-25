package com.example.analytics.di

import com.example.analytics.AnalyticsHelper
import com.example.analytics.AnalyticsManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {
    @Binds
    @Singleton
    abstract fun bindsAnalyticHelper(
        analyticsManager: AnalyticsManager
    ): AnalyticsHelper
}