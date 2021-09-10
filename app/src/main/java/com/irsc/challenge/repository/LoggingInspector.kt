package com.irsc.challenge.repository


import okhttp3.Interceptor
import okhttp3.Response
import com.google.gson.JsonParser
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import timber.log.Timber


class LoggingInspector: Interceptor {

    private fun toPrettyFormat(jsonString: String): String? {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return if (jsonString.startsWith("[")) {
            val json = JsonParser.parseString(jsonString).asJsonArray
            gson.toJson(json)
        } else if (jsonString.startsWith("{")) {
            val json = JsonParser.parseString(jsonString).asJsonObject
            gson.toJson(json)
        } else {
            jsonString
        }
    }

    fun log(message: String) {
        val maxLogSize = 2000
        for (i in 0..message.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > message.length) message.length else end
            Timber.d(String.format(" \n%s", message.substring(start, end))?:"")
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.method == "GET") {
            Timber.d(String.format("GET %s", request.url)?:"")
        }
        if (request.body != null) {
            val requestBuffer = Buffer()
            request.body!!.writeTo(requestBuffer)
            val body: String = requestBuffer.readUtf8()

            Timber.d(String.format("POST %s\n%n%s", request.url, toPrettyFormat(body)) ?: "")
        }
        val response: Response = chain.proceed(request)
        val bytesResponse = response.body!!.bytes()


        log(java.lang.String.format("RESPONSE %s\n%n", request.url))
        if (response.body!!.contentType().toString().toLowerCase().contains("json")) {
            val rawJson = String(bytesResponse)
            log(String.format("JSON \n%s", toPrettyFormat(rawJson)))
        } else {
            log(
                java.lang.String.format(
                    "ContentType \n%s",
                    response.body!!.contentType().toString()
                )
            )
        }
        return response.newBuilder().body(bytesResponse.toResponseBody(response.body!!.contentType())).build();


    }

}