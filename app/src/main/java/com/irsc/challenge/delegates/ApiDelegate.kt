package com.irsc.challenge.delegates


interface ApiCallsDelegate {
    fun showProgress(show: Boolean)
    fun toastMessage(message: String?)
}
