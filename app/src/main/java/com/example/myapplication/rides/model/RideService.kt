package com.example.myapplication.rides.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RideService {

    data class RideCreateAPI(
        val driver_id: Int,
        val origin: String,
        val destination: String,
        val capacity: Int
    )

    @GET("/rides")
    fun getAll(): Call<List<Ride>>

    @POST("/rides")
    fun create(@Body params: RideCreateAPI):  Call<Any>
}