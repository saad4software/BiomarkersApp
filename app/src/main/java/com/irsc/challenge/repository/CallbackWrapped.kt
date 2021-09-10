package com.irsc.challenge.repository

interface CallbackWrapped<T> {
    fun onResponse(response: T)
    fun onFailure(errorMessage: String?)
}
