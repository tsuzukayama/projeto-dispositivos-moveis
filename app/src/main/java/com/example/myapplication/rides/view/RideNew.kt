package com.example.myapplication.rides.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.rides.model.Ride
import com.example.myapplication.rides.model.RideDAO
import com.example.myapplication.rides.model.RideNewForm
import com.example.myapplication.session.SessionDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener

class RideNew : AppCompatActivity() {

    private lateinit var rideNewButton: Button

    private var selectedDestination: String = ""
    private var selectedOrigin: String = ""

    private lateinit var rideNewForm: RideNewForm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ride_new)

        val destinationSpinner = findViewById<Spinner>(R.id.ride_new_destination_spinner)
        val originSpinner = findViewById<Spinner>(R.id.ride_new_origin_spinner)

        val locations = resources.getStringArray(R.array.locations_array)
        selectedDestination = locations.first()
        selectedOrigin = locations.first()

        // Create an ArrayAdapter using the string array and a default spinner
        val destinationAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item, locations.toList()
        )

        val originAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item, locations.toList()
        )

        // Specify the layout to use when the list of choices appears
        destinationAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        originAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        destinationSpinner.adapter = destinationAdapter
        originSpinner.adapter = originAdapter

        destinationSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {
                selectedDestination = parent.getItemAtPosition(position).toString()
                rideNewForm.destination = selectedDestination
                Log.v("item", parent.getItemAtPosition(position) as String)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        originSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {
                selectedOrigin = parent.getItemAtPosition(position).toString()
                rideNewForm.origin = selectedOrigin
                Log.v("item", parent.getItemAtPosition(position) as String)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    override fun onStart() {
        super.onStart()

        rideNewButton = findViewById(R.id.ride_new_button)

        val loggedUser = SessionDAO.instance.getLoggedUser()

        val userId = loggedUser?.id ?: 0
        rideNewForm = RideNewForm(
            userId,
            findViewById(R.id.ride_new_capacity_input),
            selectedOrigin,
            selectedDestination
        )

        val createRideCallback: Callback<Ride> = object: Callback<Ride> {
            override fun onResponse(call: Call<Ride>, response: Response<Ride>) {
                if(response.isSuccessful) {
                    response.body()?.id?.let {
                        Toast.makeText(App.context, "Carona criada com sucesso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(App.context, RideDetailsDriver::class.java)
                        val bundle = Bundle()
                        bundle.putInt("id", it)
                        intent.putExtras(bundle)
                        startActivity(intent, null)
                    }
                } else {
                    Toast.makeText(App.context, "Erro ao criar carona", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Ride>, t: Throwable) {
                Log.e("onFailure error", t?.message)
            }
        }

        rideNewButton.setOnClickListener {

            if(rideNewForm.isValid()) {
                RideDAO.add(rideNewForm.values(), createRideCallback)
            }
            else rideNewForm.errors.forEach{(id, error) -> findViewById<EditText>(id).error = error}

        }
    }
}