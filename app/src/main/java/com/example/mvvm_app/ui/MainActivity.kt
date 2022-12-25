package com.example.mvvm_app.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_app.R
import com.example.mvvm_app.data.network.APIClient
import com.example.mvvm_app.data.repo.GetUserRepo
import com.example.mvvm_app.utils.Resource

class MainActivity : AppCompatActivity() {
    lateinit var mainViewAdaptor: MainViewAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiInterface = APIClient.APIClient.getClient()
        val getUserRepo = GetUserRepo(apiInterface)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val viewModel =
            ViewModelProvider(this, MainViewModelFactory(getUserRepo))[MainViewModel::class.java]

        recyclerView.apply {
            val layoutManagers = LinearLayoutManager(this@MainActivity)
            layoutManager = layoutManagers
            addItemDecoration(DividerItemDecoration(this@MainActivity, layoutManagers.orientation))
            mainViewAdaptor = MainViewAdaptor()
            adapter = mainViewAdaptor

        }

        viewModel.userList.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> it.data?.let { it1 -> mainViewAdaptor.updateList(it1) }
                Resource.Status.ERROR -> it.message?.let { it1 -> Log.d("Error", it1) }
                Resource.Status.LOADING -> {}
            }
        })

    }
}