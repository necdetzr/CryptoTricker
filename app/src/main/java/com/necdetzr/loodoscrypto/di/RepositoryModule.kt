package com.necdetzr.loodoscrypto.di

import com.necdetzr.loodoscrypto.data.repository.CoinRepositoryImpl
import com.necdetzr.loodoscrypto.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCoinRepository(
        coinRepositoryImpl: CoinRepositoryImpl
    ): CoinRepository

}