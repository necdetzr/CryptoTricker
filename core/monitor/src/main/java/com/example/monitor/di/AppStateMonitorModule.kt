package com.example.monitor.di

import com.example.monitor.AppStateMonitor
import com.example.monitor.imp.DefaultAppStateMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppStateMonitorModule {
    @Binds
    internal abstract fun bindsAppStateMonitor(
        appStateMonitor: DefaultAppStateMonitor
    ) : AppStateMonitor
}