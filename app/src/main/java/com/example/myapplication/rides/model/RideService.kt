package com.example.myapplication.rides.model

import retrofit2.Call
import retrofit2.http.*

interface RideService {

    data class RideCreateAPI(
        val driver_id: Int,
        val origin: String,
        val destination: String,
        val capacity: Int
    )

    @GET("/rides")
    fun getAll(): Call<List<Ride>>

    @GET("/rides/{id}")
    fun get(@Path("id") id: Int): Call<Ride>

    @POST("/rides")
    fun create(@Body params: RideCreateAPI):  Call<Ride>

    @POST("/rides/{id}/start")
    fun startRide(@Path("id") id: Int): Call<Any?>

    @POST("/rides/{id}/end")
    fun finishRide(@Path("id") id: Int): Call<Any?>
}