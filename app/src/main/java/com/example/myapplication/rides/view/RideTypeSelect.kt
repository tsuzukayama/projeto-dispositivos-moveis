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

        var loggedUser = SessionDAO.getLoggedUser()
        var id = loggedUser?.id ?: 0

        var call = RetrofitInitializer().userService().getActiveRide(id)

        call.enqueue(object: Callback<UserService.CurrentRide> {
            override fun onResponse(call: Call<UserService.CurrentRide>, response: Response<UserService.CurrentRide>) {
                response.body().let {

                    when (it?.user_role) {
                        "driver"-> {
                            val intent = Intent(App.context, RideDetailsDriver::class.java)

                            val bundle = Bundle()
                            bundle.putInt("id", it.ride.id)
                            intent.putExtras(bundle)
                            startActivity(intent, null)
                        }
                        "passenger" -> {
                            val intent = Intent(App.context, RideDetailsPassenger::class.java)

                            val bundle = Bundle()
                            bundle.putInt("id", it.ride.id)
                            intent.putExtras(bundle)
                            startActivity(intent, null)
                        }
                    }

                }
            }

            override fun onFailure(call: Call<UserService.CurrentRide>, t: Throwable) {
                Log.d("RideDAO", "error")
            }
        })



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
