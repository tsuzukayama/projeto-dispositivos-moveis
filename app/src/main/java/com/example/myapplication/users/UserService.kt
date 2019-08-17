package com.example.myapplication.users

import com.example.myapplication.rides.model.Ride
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    data class UserCreateAPI(val username: String, val password: String, val password_confirmation: String)

    data class CurrentRide(val ride: Ride, val user_role: String)
    @POST("/users")
    fun create(@Body params: UserCreateAPI): Call<Any>

    @GET("/users/{id}/active_ride")
    fun getActiveRide(@Path("id") userId: Int): Call<CurrentRide>
}