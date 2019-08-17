package com.example.myapplication.rides.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.RetrofitInitializer
import com.example.myapplication.rides.model.Ride
import com.example.myapplication.rides.model.RideDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RideList :AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_list)

        populateRides()
    }

    override fun onStart() {
        super.onStart()


        var call = RetrofitInitializer().rideService().getAll()

        call.enqueue(object: Callback<List<Ride>> {
            override fun onResponse(call: Call<List<Ride>>, response: Response<List<Ride>>) {
                response.body().let {

                    if (it != null) {
                        RideAdapter().apply {
                            rides = it as ArrayList<Ride>
                            recyclerView.adapter = this
                            (recyclerView.adapter as RideAdapter).notifyDataSetChanged()

                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Ride>>, t: Throwable) {
                Log.d("RideDAO", "error")
            }
        })
    }


    private fun populateRides() {

        recyclerView = findViewById(R.id.list_rides)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

    }
}