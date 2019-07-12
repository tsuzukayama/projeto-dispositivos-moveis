package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserNew : AppCompatActivity() {

    private lateinit var createUserButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.user_new)
    }

    override fun onStart() {
        super.onStart()

        createUserButton = findViewById(R.id.create_user_button)

        createUserButton.setOnClickListener{
            validatesUser()
            Toast.makeText(App.context, "Button clicked", Toast.LENGTH_SHORT).show()
        }
    }

    fun validatesUser() {

    }
}