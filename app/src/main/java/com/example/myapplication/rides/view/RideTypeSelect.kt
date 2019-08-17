package com.example.myapplication.rides.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.R

class RideTypeSelect : AppCompatActivity() {

    private lateinit var rideNewButton: Button
    private lateinit var rideListButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.ride_type_select)
    }

    override fun onStart() {
        super.onStart()

        rideNewButton = findViewById(R.id.ride_new_button)
        rideListButton = findViewById(R.id.ride_list_button)

        rideNewButton.setOnClickListener {
            val intent = Intent(App.context, RideNew::class.java)
            startActivity(intent)
        }
        rideListButton.setOnClickListener {
            val intent = Intent(App.context, RideList::class.java)
            startActivity(intent)
        }
    }
}
