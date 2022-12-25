package com.example.mvvm_app.data.network

import com.example.mvvm_app.data.model.LoginUser
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("login")
    suspend fun getLoginList():List<LoginUser>
}