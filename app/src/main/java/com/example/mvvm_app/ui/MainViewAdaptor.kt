package com.example.mvvm_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mvvm_app.R
import com.example.mvvm_app.data.model.LoginUser

class MainViewAdaptor() :Adapter<MainViewAdaptor.MainViewHolder>() {

    private var userList:ArrayList<LoginUser> =ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.main_item,parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(index:Int): LoginUser{
        return  userList[index]
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textName= view.findViewById<TextView>(R.id.textName)
        val image= view.findViewById<ImageView>(R.id.imageView)
        fun bind(item: LoginUser) {
            textName.text= item.name
        }
    }

    fun updateList(list: List<LoginUser>){
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }
}