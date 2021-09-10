package com.irsc.challenge.repository

import com.irsc.challenge.models.MarkModel
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("biomarkers")
    fun biomarkers(): Call<List<MarkModel>>

}