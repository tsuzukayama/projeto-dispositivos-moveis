package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class UserNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide()
        setContentView(R.layout.user_new)
    }
}