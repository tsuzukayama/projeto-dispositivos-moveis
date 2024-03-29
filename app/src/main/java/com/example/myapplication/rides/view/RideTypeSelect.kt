package com.example.myapplication.rides.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.RetrofitInitializer
import com.example.myapplication.rides.model.Ride
import com.example.myapplication.session.SessionDAO
import com.example.myapplication.users.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
