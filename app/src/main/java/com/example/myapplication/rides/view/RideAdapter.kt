package com.example.myapplication.rides.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.rides.model.Ride

class RideAdapter : RecyclerView.Adapter<RideAdapter.RideViewHolder>() {

    inner class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val departure: TextView = itemView.findViewById(R.id.ride_departure)
        val destination: TextView = itemView.findViewById(R.id.ride_destination)
        val driver: TextView = itemView.findViewById(R.id.ride_driver)
        val capacity: TextView = itemView.findViewById(R.id.ride_capacity)

        lateinit var ride: Ride
    }

    var rides: ArrayList<Ride> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        return RideViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ride_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        holder.ride = rides[position]

        holder.departure.text = rides[position].origin
        holder.destination.text = rides[position].destination
        holder.driver.text = rides[position].driver.username
        holder.capacity.text = rides[position].capacity.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(App.context, RideDetailsPassenger::class.java)

            val bundle = Bundle()
            bundle.putInt("id", holder.ride.id)
            intent.putExtras(bundle)
            ContextCompat.startActivity(it.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return rides.size
    }
}
