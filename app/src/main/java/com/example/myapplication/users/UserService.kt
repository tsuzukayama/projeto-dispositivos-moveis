package com.example.myapplication.users

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    data class UserCreateAPI(val username: String, val password: String, val password_confirmation: String)

    @POST("/users")
    fun create(@Body params: UserCreateAPI): Call<Any>
}