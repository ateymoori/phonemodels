package com.phonemodels.domain.usecases

//T input parameters, R outputs
abstract class UseCase<T, R> {
    abstract suspend fun invoke(data: T? = null): R
}