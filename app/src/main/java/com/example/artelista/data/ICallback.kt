package com.example.artelista.data

interface ICallback<T> {
    fun onSuccess(result: T?)
    fun onFailed(exception: Exception)
}
