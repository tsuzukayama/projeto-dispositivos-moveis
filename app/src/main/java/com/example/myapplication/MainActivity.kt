package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.myapplication.rides.RideTypeSelect
import com.example.myapplication.session.SessionDAO
import com.example.myapplication.session.SessionService
import com.example.myapplication.users.UserNew
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var newUserLink: TextView
    private lateinit var loginButton: Button
    private lateinit var loading: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        newUserLink = findViewById(R.id.create_user_link)
        loginButton = findViewById(R.id.login_button)
        loading = findViewById(R.id.loading)



        newUserLink.setOnClickListener{
            startActivity(Intent(App.context, UserNew::class.java))
        }

        loginButton.setOnClickListener{

            showLoading()

            val username = findViewById<EditText>(R.id.login_user_input).text.toString()
            val password = findViewById<EditText>(R.id.password_input).text.toString()

            SessionDAO.add(
                SessionService.SessionCreateAPI(username, password),
                object: Callback<Any> {
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        if(response.isSuccessful) {
                        } else {
                        }
                        startActivity(Intent(App.context, RideTypeSelect::class.java))
                        hideLoading()
                    }
                    override fun onFailure(call: Call<Any>, t: Throwable?) {
                        Log.e("onFailure error", t?.message)
                        hideLoading()
                    }
                })
        }
    }

    fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    fun showLoading() {
        loading.visibility = View.VISIBLE
    }
}
