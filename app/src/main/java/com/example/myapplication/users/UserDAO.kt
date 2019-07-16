package com.example.myapplication.users

import android.util.Log
import com.example.myapplication.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserDAO {
    fun add(params: UserCreateAPI) {
        val call = RetrofitInitializer().userService().create(params)
        call.enqueue(object: Callback<Any> {
            override fun onResponse(call: Call<Any>?,
                                    response: Response<Any>?) {
            }

            override fun onFailure(call: Call<Any>?,
                                   t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }
}