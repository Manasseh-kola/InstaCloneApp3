package com.example.instacloneapp3.core.util

typealias SimpleUILoadState = UILoadState<Unit>

sealed class UILoadState<T>(val data :T? = null, val message: String? = null) {
    class Loading<T>(data : T? = null): UILoadState<T>(data)
    class Success<T>(data: T?): UILoadState<T>(data)
    class Error<T>(data: T? = null, message: String): UILoadState<T>(data, message)
}