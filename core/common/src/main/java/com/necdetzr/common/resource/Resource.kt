package com.necdetzr.common.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>

    data class Error(val exception: Throwable? = null) : Resource<Nothing>

    data object Loading : Resource<Nothing>
}

fun <T> Flow<T>.asResource(): Flow<Resource<T>>{
    return this.map<T,Resource<T>>{Resource.Success(it)}
        .onStart { emit(Resource.Loading) }
        .catch { emit(Resource.Error(it)) }
}

fun <T> Flow<T>.asFireAndForgetResource(): Flow<Resource<Unit>>{
    return this.map<T, Resource<Unit>> { Resource.Success(Unit) }
        .onStart { emit(Resource.Loading) }
        .catch { emit(Resource.Error(it)) }
}
