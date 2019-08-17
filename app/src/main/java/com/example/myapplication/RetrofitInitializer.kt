package com.example.myapplication

import com.example.myapplication.rides.model.RideService
import com.example.myapplication.session.SessionService
import com.example.myapplication.users.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:3000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

    fun userService(): UserService {
        return retrofit.create(UserService::class.java)
    }

    fun sessionService(): SessionService {
        return retrofit.create(SessionService::class.java)
    }

    fun rideService(): RideService {
        return retrofit.create(RideService::class.java)
    }
}