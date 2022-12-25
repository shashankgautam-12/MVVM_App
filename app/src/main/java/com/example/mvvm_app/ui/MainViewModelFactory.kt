package com.example.mvvm_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_app.data.repo.GetUserRepo

class MainViewModelFactory(private val userRepo: GetUserRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.userRepo) as T
        }else throw java.lang.IllegalArgumentException("View Model not found")
    }
}