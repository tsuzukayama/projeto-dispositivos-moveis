package com.example.myapplication

import com.example.myapplication.users.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
                            .baseUrl("http://192.168.15.17:3000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

    fun userService(): UserService {
        return retrofit.create(UserService::class.java)
    }
}