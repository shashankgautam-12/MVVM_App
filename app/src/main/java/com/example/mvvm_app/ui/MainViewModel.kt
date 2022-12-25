package com.example.mvvm_app.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_app.data.model.LoginUser
import com.example.mvvm_app.data.repo.GetUserRepo
import com.example.mvvm_app.utils.Resource
import kotlinx.coroutines.*

class MainViewModel(private val getUserRepo: GetUserRepo): ViewModel() {
    val userList:MutableLiveData<Resource<List<LoginUser>>> =MutableLiveData()

    init {
        getUserList()
    }

    private fun getUserList(){
        userList.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("View Model", Thread.currentThread().name)
            try {
                withContext(Dispatchers.Main) {
                    Log.d("View Model", Thread.currentThread().name)
                    val users= getUserRepo.hitLoginUser()
                    userList.postValue(Resource.success(users))
                }
            }catch (e:Exception){
                userList.postValue(Resource.error(e.toString()))
            }
        }
    }

}