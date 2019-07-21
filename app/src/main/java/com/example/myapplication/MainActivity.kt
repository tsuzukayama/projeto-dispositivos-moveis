package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.users.UserNew

class MainActivity : AppCompatActivity() {

    private lateinit var newUserLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        newUserLink = findViewById(R.id.create_user_link)
        newUserLink.setOnClickListener{
            val intent = Intent(App.context, UserNew::class.java)
            startActivity(intent)
        }
    }
}
