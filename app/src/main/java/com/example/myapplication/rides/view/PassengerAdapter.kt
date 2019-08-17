package com.example.myapplication.rides.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.users.User

class PassengerAdapter : RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder>() {

    inner class PassengerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val email: TextView = itemView.findViewById(R.id.passenger_email)
        val phone: TextView = itemView.findViewById(R.id.passenger_phone)

        lateinit var passenger: User
    }

    var passengers: ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder {
        return PassengerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.passenger_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) {
        holder.passenger = passengers[position]

        holder.email.text = passengers[position].email
        holder.phone.text = passengers[position].phone
    }

    override fun getItemCount(): Int {
        return passengers.size
    }
}
