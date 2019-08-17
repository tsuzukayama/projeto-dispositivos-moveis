package com.example.myapplication.rides.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.RetrofitInitializer
import com.example.myapplication.rides.model.Ride
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RideDetailsDriver : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var recyclerView: RecyclerView

    private var rideId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_details_driver)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val intent = this.intent
        val bundle = intent.extras

        rideId = bundle?.getInt("id") ?: 0

        recyclerView = findViewById(R.id.list_passengers)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
    }

    override fun onStart() {
        super.onStart()

        var call = RetrofitInitializer().rideService().get(rideId)

        call.enqueue(object: Callback<Ride> {
            override fun onResponse(call: Call<Ride>, response: Response<Ride>) {
                response.body().let {

                    if (it != null) {
                        PassengerAdapter().apply {
                            passengers = it.passengers
                            recyclerView.adapter = this
                            (recyclerView.adapter as PassengerAdapter).notifyDataSetChanged()

                        }
                    }
                }
            }

            override fun onFailure(call: Call<Ride>, t: Throwable) {
                Log.d("RideDAO", "error")
            }
        })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12f))
    }
}
