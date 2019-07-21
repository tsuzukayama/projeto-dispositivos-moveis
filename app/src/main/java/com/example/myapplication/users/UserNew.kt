package com.example.myapplication.users

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.App
import com.example.myapplication.MainActivity
import com.example.myapplication.R

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

        createUserButton.setOnClickListener {

            val userNewForm = UserNewForm(
                findViewById(R.id.user_input),
                findViewById(R.id.password_input),
                findViewById(R.id.confirm_password_input)
            )

            if (userNewForm.isValid()) {
                UserDAO.add(userNewForm.values())
                Toast.makeText(App.context, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(App.context, MainActivity::class.java)
                startActivity(intent)
            }
            else userNewForm.errors.forEach{(id, error) -> findViewById<EditText>(id).error = error }
        }
    }
}