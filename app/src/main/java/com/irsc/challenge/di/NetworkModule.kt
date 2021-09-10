package com.irsc.challenge.di

import com.irsc.challenge.repository.Api
import com.irsc.challenge.repository.LoggingInspector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val baseUrl = "https://retoolapi.dev/hZZ5j8/"

    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(LoggingInspector())
            .build()
    }

    @Provides
    fun provideApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }


}

