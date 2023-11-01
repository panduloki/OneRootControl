package com.example.onerootcontrol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Recycler view
// https://www.youtube.com/watch?v=VVXKVFyYQdQ

// when recycler item clicked navigate to new view
// https://www.youtube.com/watch?v=WqrpcWXBz14

class MyAdapter(private  val userList: ArrayList<User>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.username.text = currentItem.username
        holder.location.text = currentItem.location
        holder.noOfSessions.text= currentItem.numberOfSessions.toString()
        holder.role.text = currentItem.role
        holder.mobileNo.text = currentItem.mobileNo
        holder.noOfCoconuts.text = currentItem.numberOfCoconuts.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val username : TextView = itemView.findViewById(R.id.username)
        val location : TextView = itemView.findViewById(R.id.location)
        val role : TextView = itemView.findViewById(R.id.role)
        var noOfSessions : TextView = itemView.findViewById(R.id.SessionsNo)
        var noOfCoconuts : TextView = itemView.findViewById(R.id.coconutNo)
        val mobileNo : TextView = itemView.findViewById(R.id.mobileNo)
    }


}