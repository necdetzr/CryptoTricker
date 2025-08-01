package com.necdetzr.testing.testing.di

import com.necdetzr.common.network.CryptoDispatchers
import com.necdetzr.common.network.Dispatcher
import com.necdetzr.common.network.di.DispatchersModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class]
)
internal object TestDispatchersModule {
    @Provides
    @Dispatcher(CryptoDispatchers.IO)
    fun providesIODispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher = testDispatcher

    @Provides
    @Dispatcher(CryptoDispatchers.Default)
    fun providesDefaultDispatcher(testDispatcher: TestDispatcher) : CoroutineDispatcher = testDispatcher

}