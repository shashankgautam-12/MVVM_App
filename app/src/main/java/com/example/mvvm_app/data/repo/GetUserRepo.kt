package com.example.mvvm_app.data.repo

import com.example.mvvm_app.data.model.LoginUser
import com.example.mvvm_app.data.network.ApiInterface

class GetUserRepo(private val apiInterface: ApiInterface) {
    suspend fun hitLoginUser(): List<LoginUser> {
        return apiInterface.getLoginList()
    }
}