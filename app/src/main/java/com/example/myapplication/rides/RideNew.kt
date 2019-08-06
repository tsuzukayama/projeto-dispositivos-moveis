package com.example.myapplication.rides

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.R


class RideNew : AppCompatActivity() {

    private lateinit var rideNewButton: Button
    private lateinit var departureInput: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_new)
    }

    override fun onStart() {
        super.onStart()

        rideNewButton = findViewById(R.id.ride_new_button)
        departureInput = findViewById(R.id.ride_new_departure_input)

        rideNewButton.setOnClickListener {
            val intent = Intent(App.context, RideDetailsDriver::class.java)
            startActivity(intent)
        }
    }
}