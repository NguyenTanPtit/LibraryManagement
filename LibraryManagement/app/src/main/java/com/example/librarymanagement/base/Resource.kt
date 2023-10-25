package com.example.librarymanagement.base

sealed class Resource<T> {

    class Loading<T> : Resource<T>()
    class Error<T>(val error: Throwable) : Resource<T>() {
    }
    class Success<T>(val data: T) : Resource<T>()
}

fun <T> Resource<T>.onResult(
    onSuccess: (success: T) -> Unit,
    onError: (throwable: Throwable) -> Unit,
    onLoading: (() -> Unit)? = null
) {
    when (this) {
        is Resource.Error -> onError(error)
        is Resource.Success -> onSuccess(data)
        else -> onLoading?.invoke()
    }
}

fun <T> Resource<T>.doOnSuccess(callback: (success: T) -> Unit) {
    if (this is Resource.Success) callback(data)
}

fun <T> Resource<T>.doOnError(callback: (throwable: Throwable) -> Unit) {
    if (this is Resource.Error) callback(error)
}

fun <T> Resource<T>.doOnLoading(callback: () -> Unit) {
    if (this is Resource.Loading) {
        callback()
    }
}


fun <Z, T> Resource<T>.map(map: (T) -> Z): Resource<Z> {
    return when(this) {
        is Resource.Error -> Resource.Error(error)
        is Resource.Success -> {
            Resource.Success(map(this.data))
        }
        is Resource.Loading -> {
            Resource.Loading()
        }
    }
}

val <T> Resource<T>.successData
    get() = when(this) {
        is Resource.Error -> null
        is Resource.Success -> data
        else -> null
    }

val <T> Resource<T>.errorData
    get() = when(this) {
        is Resource.Error -> Resource.Error<T>(this.error)
        is Resource.Success -> null
        else -> null
    }
