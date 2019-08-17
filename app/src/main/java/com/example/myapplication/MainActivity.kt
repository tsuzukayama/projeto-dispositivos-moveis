package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.Session
import android.util.Log
import android.view.View
import android.widget.*
import com.example.myapplication.rides.view.RideDetailsDriver
import com.example.myapplication.rides.view.RideDetailsPassenger
import com.example.myapplication.rides.view.RideTypeSelect
import com.example.myapplication.session.SessionDAO
import com.example.myapplication.session.SessionService
import com.example.myapplication.users.User
import com.example.myapplication.users.UserNew
import com.example.myapplication.users.UserService
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
                object: Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful) {
                            response.body()?.let { it1 -> SessionDAO.instance.setUser(it1) }
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
                                            else -> {
                                                startActivity(Intent(App.context, RideTypeSelect::class.java))
                                            }
                                        }

                                    }
                                }

                                override fun onFailure(call: Call<UserService.CurrentRide>, t: Throwable) {
                                    Log.d("RideDAO", "error")
                                }
                            })
                        }
                        hideLoading()
                    }
                    override fun onFailure(call: Call<User>, t: Throwable?) {
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
