package com.example.myapplication.users

import com.example.myapplication.RetrofitInitializer
import retrofit2.Callback

object UserDAO {
    fun add(params: UserService.UserCreateAPI, callback: Callback<Any>) {
        RetrofitInitializer().userService().create(params)
            .enqueue(callback)
    }
}