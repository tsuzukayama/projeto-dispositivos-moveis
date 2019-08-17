package com.example.myapplication.rides.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.rides.model.RideDAO
import com.example.myapplication.rides.model.RideNewForm
import com.example.myapplication.session.SessionDAO
import com.example.myapplication.users.UserNewForm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RideNew : AppCompatActivity() {

    private lateinit var rideNewButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_new)
    }

    override fun onStart() {
        super.onStart()

        rideNewButton = findViewById(R.id.ride_new_button)

        val loggedUser = SessionDAO.instance.getLoggedUser()

        val userId = loggedUser?.id ?: 0
        val rideNewForm = RideNewForm(
            userId,
            findViewById(R.id.ride_new_capacity_input),
            findViewById(R.id.ride_new_departure_input),
            findViewById(R.id.ride_new_destination_input)
        )

        val createRideCallback: Callback<Any> = object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful) {
                    Toast.makeText(App.context, "Carona criada com sucesso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(App.context, MainActivity::class.java))
                } else {
                    Toast.makeText(App.context, "Erro ao criar carona", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("onFailure error", t?.message)
            }
        }

        rideNewButton.setOnClickListener {

            if(rideNewForm.isValid()) {
                RideDAO.add(rideNewForm.values(), createRideCallback)
                val intent = Intent(App.context, RideDetailsDriver::class.java)
                startActivity(intent)
            }
            else rideNewForm.errors.forEach{(id, error) -> findViewById<EditText>(id).error = error}

        }
    }
}