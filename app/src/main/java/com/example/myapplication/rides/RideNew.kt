package com.example.myapplication.rides

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.R

class RideNew : AppCompatActivity() {

    private lateinit var rideNewButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_new)
    }

    override fun onStart() {
        super.onStart()

        rideNewButton = findViewById(R.id.ride_new_button)

        rideNewButton.setOnClickListener {
            val intent = Intent(App.context, RideDetailsDriver::class.java)
            startActivity(intent)
        }
    }
}