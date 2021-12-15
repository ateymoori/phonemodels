package com.phonemodels.data.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Loading<out T>(val msg: String?=null) : Resource<T>()
    sealed class Failure(val error: String?) : Resource<Nothing>() {
        class Generic(e: String?) : Failure(e)
        class NetworkException(e: String?) : Failure(e)
        class UnAuthorized(e: String?) : Failure(e)
    }
}


inline fun <T> Resource<T>.onSuccess(body: (T?) -> Unit): Resource<T> {
    if (this is Resource.Success) body(data)
    return this
}

inline fun <T> Resource<T>.onNetworkError(body: (String) -> Unit): Resource<T> {
    if (this is Resource.Failure.NetworkException) error?.let { body(it) }
    return this
}

inline fun <T> Resource<T>.unAuthorized(body: (String) -> Unit): Resource<T> {
    if (this is Resource.Failure.UnAuthorized) error?.let { body(it) }
    return this
}

inline fun <T> Resource<T>.onError(body: (String) -> Unit) : Resource<T> {
    if (this is Resource.Failure.Generic) error?.let { body(it) }
    return this
}
inline fun <T> Resource<T>.onLoading(body: (Any) -> Unit ) : Resource<T> {
    return this
}