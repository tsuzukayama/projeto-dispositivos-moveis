package com.example.myapplication.rides

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.App
import com.example.myapplication.R

class RideAdapter : BaseAdapter() {

    override fun getCount(): Int {
        return RideDAO.instance.size()
    }

    override fun getItem(position: Int): Any {
        return RideDAO.instance.getItemAt(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = App.context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val localView = convertView ?: inflater.inflate(R.layout.ride_list_item, null)
        val ride = RideDAO.instance.getItemAt(position)
        val departure = localView.findViewById<TextView>(R.id.ride_departure)
        val destination = localView.findViewById<TextView>(R.id.ride_destination)

        departure.text = ride.departure
        destination.text = ride.destination

        return localView
    }
}
