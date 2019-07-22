package com.example.myapplication.users

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserNew : AppCompatActivity() {

    private lateinit var createUserButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.user_new)
    }

    override fun onStart() {
        super.onStart()

        createUserButton = findViewById(R.id.create_user_button)

        val userNewForm = UserNewForm(
            findViewById(R.id.user_input),
            findViewById(R.id.password_input),
            findViewById(R.id.confirm_password_input)
        )

        val createUserCallBack: Callback<Any> = object: Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response.isSuccessful) {
                    Toast.makeText(App.context, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(App.context, MainActivity::class.java))
                } else {
                    Toast.makeText(App.context, "Erro ao criar usuário", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.e("onFailure error", t?.message)
            }
        }

        createUserButton.setOnClickListener {
            if (userNewForm.isValid()) {
                UserDAO.add(userNewForm.values(), createUserCallBack)
            }
            else userNewForm.errors.forEach{(id, error) -> findViewById<EditText>(id).error = error }
        }


    }
}