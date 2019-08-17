package com.example.myapplication.rides.model

import com.example.myapplication.RetrofitInitializer
import retrofit2.Callback
import retrofit2.http.POST


object RideDAO {
    private var rides: MutableList<Ride> = ArrayList()

    val instance = this


    fun add(params: RideService.RideCreateAPI, callback: Callback<Any>) {
        RetrofitInitializer().rideService().create(params)
            .enqueue(callback)
    }

    fun size(): Int {
        return rides.size
    }

    fun getItemAt(pos: Int): Ride {
        return rides[pos]
    }

    fun getAll(): List<Ride> {
        return rides
    }

    fun addAll(ridesToAdd: List<Ride>) {
        rides.addAll(ridesToAdd)
    }
}