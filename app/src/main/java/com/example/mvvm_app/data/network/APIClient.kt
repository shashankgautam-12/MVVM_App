package com.example.mvvm_app.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {
    const val BASE_URL = "https://60b4e6b2fe923b0017c83076.mockapi.io/api/v1/"
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    object APIClient {
        val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES).addInterceptor(
            interceptor
        ).build()

        fun getClient(): ApiInterface {
            return Retrofit.Builder().client(client).baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiInterface::class.java)
        }
    }
}