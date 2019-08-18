package com.example.myapplication.rides.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.RetrofitInitializer
import com.example.myapplication.rides.model.Ride
import com.example.myapplication.rides.model.RideDAO
import com.example.myapplication.rides.model.RideService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RideDetailsDriver : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var recyclerView: RecyclerView
    private lateinit var startFinishRideButton: Button

    private var rideId: Int = 0
    private lateinit var ride: Ride

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

        startFinishRideButton = findViewById(R.id.start_finish_ride_button)
        recyclerView = findViewById(R.id.list_passengers)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        setOnClickListeners()
    }

    override fun onStart() {
        super.onStart()

        var call = RetrofitInitializer().rideService().get(rideId)

        call.enqueue(object: Callback<Ride> {
            override fun onResponse(call: Call<Ride>, response: Response<Ride>) {
                response.body().let {

                    if (it != null) {
                        ride = it

                        val locations = resources.getStringArray(R.array.locations_array)
                        val latitudes = resources.getStringArray(R.array.locations_lat)
                        val longitudes = resources.getStringArray(R.array.locations_long)

                        val destinationIndex = locations.indexOfFirst { location -> location == ride.destination }
                        val originIndex = locations.indexOfFirst { location -> location == ride.origin }

                        val destinationLat = latitudes[destinationIndex].toDouble()
                        var destinationLong = longitudes[destinationIndex].toDouble()

                        val originLat = latitudes[originIndex].toDouble()
                        val originLong = longitudes[originIndex].toDouble()

                        // Add a marker in Sydney and move the camera
                        val destination = LatLng(destinationLat, destinationLong)
                        val origin = LatLng(originLat, originLong)
                        mMap.addMarker(MarkerOptions().position(destination).title("Marker in destination"))
                        mMap.addMarker(MarkerOptions().position(origin).title("Marker in origin"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 12f))

                        val color = 0xffF57F17

                        val polyline1 = mMap.addPolyline(
                            PolylineOptions()

                                .clickable(true)
                                .add(
                                    destination,
                                    origin
                                )
                        ).setColor(color.toInt())


                        when (ride.status) {
                            "created" -> {
                                startFinishRideButton.text = "Iniciar"
                            }
                            "started"-> {
                                startFinishRideButton.text = "Terminar"
                            }
                            "ended" -> {
                                startFinishRideButton.isEnabled = false
                            }
                        }

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

    }

    val startRideCallback: Callback<Any?> = object: Callback<Any?> {
        override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
           if(response.isSuccessful) {
               startFinishRideButton.text = "Terminar"
               ride.status = "started"
           }
        }

        override fun onFailure(call: Call<Any?>, t: Throwable) {
            Log.e("onFailure error", t?.message)
        }
    }

    val finishRideCallback: Callback<Any?> = object: Callback<Any?> {
        override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
            if(response.isSuccessful) {
                startActivity(Intent(App.context, RideTypeSelect::class.java))
            }

        }

        override fun onFailure(call: Call<Any?>, t: Throwable) {
            Log.e("onFailure error", t?.message)
        }
    }

    fun setOnClickListeners() {
        startFinishRideButton.setOnClickListener {
            when (ride.status) {
                "created" -> {
                    RideDAO.startRide(ride.id, startRideCallback)
                }
                "started"-> {
                    RideDAO.finishRide(ride.id, finishRideCallback)
                }
                "ended" -> {

                }
            }
        }
    }
}
