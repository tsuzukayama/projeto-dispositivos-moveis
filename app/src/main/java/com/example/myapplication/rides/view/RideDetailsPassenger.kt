package com.example.myapplication.rides.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.RetrofitInitializer
import com.example.myapplication.rides.model.Ride

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

class RideDetailsPassenger : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var driverNameText: TextView
    private lateinit var capacityText: TextView
    private lateinit var phoneText: TextView
    private lateinit var emailText: TextView

    private var rideId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_details_passenger)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val intent = this.intent
        val bundle = intent.extras

        rideId = bundle?.getInt("id") ?: 0

        setFields()
    }

    override fun onStart() {
        super.onStart()

        var call = RetrofitInitializer().rideService().get(rideId)

        call.enqueue(object: Callback<Ride> {
            override fun onResponse(call: Call<Ride>, response: Response<Ride>) {
                response.body().let {

                    if (it != null) {
                        driverNameText.text = it.driver.username
                        capacityText.text = it.capacity.toString()
                    }
                }
            }

            override fun onFailure(call: Call<Ride>, t: Throwable) {
                Log.d("RideDAO", "error")
            }
        })
    }

    private fun setFields() {
        driverNameText = findViewById(R.id.ride_details_driver_name)
        capacityText = findViewById(R.id.ride_details_capacity)
        phoneText = findViewById(R.id.ride_details_phone_label)
        emailText = findViewById(R.id.ride_details_email_label)
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
        val sydney = LatLng(10.0, 10.0)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8f))

        mMap.addMarker(
            MarkerOptions()
                .position(LatLng(10.0, 10.0))
                .title("Hello world")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(LatLng(12.0, 10.0))
                .title("Hello world")
        )

        val color = 0xffF57F17

        val polyline1 = googleMap.addPolyline(
            PolylineOptions()

                .clickable(true)
                .add(
                    LatLng(12.0, 10.0),
                    LatLng(10.0, 10.0)
                )
        ).setColor(color.toInt())
    }
}
