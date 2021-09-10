package com.irsc.challenge.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.annotation.NonNull
import retrofit2.Callback
import com.irsc.challenge.delegates.ApiCallsDelegate
import okio.IOException
import retrofit2.Call
import retrofit2.Response

import com.irsc.challenge.models.*
import javax.inject.Inject


class ApiCalls
@Inject
constructor(
    private val api: Api,
    ) {

    var delegate: ApiCallsDelegate? = null

    private fun <T> convertCallback(callback: CallbackWrapped<T>): Callback<T> {

        delegate?.showProgress(true)
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                delegate?.showProgress(false)
                if (response.errorBody() != null) {
                    try {
                        val msg = response.errorBody()?.string()
                        delegate?.toastMessage(msg)
                        callback.onFailure(msg)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    return
                }
                if (response.body() != null) {
                    val body: T? = response.body()
                    callback.onResponse(body!!)

                } else {
                    callback.onFailure("Unknown exception")
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                val msg = throwable.message
                if (msg != null && !msg.contains("closed") && !msg.contains("Canceled")) {
                    delegate?.toastMessage(msg)
                }
                callback.onFailure(msg)
                delegate?.showProgress(false)
            }
        }
    }

    fun getBioMarks(callback: CallbackWrapped<List<MarkModel>>){
        api.biomarkers().enqueue(convertCallback(callback))
    }



}

