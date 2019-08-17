package com.example.myapplication.session

import com.example.myapplication.users.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SessionService {
    data class SessionCreateAPI(val username: String, val password: String)

    @POST("/sessions")
    fun create(@Body params: SessionCreateAPI): Call<User>
}