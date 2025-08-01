package com.necdetzr.common.network

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val cryptoDispatcher:CryptoDispatchers)

enum class CryptoDispatchers{
    Default,
    IO

}