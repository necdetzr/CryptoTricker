package com.necdetzr.common.base

interface UseCase<P, out T : Any?> {
    suspend operator fun invoke(params: P): T
}