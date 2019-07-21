package com.example.myapplication.users

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/users")
    fun create(@Body params: UserCreateAPI): Call<Any>
}