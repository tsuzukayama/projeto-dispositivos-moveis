package com.example.myapplication.session

import com.example.myapplication.RetrofitInitializer
import com.example.myapplication.users.User
import retrofit2.Callback

object SessionDAO {

    val instance = this
    private var loggedUser: User? = null

    fun add(params: SessionService.SessionCreateAPI, callback: Callback<User>) {
        RetrofitInitializer().sessionService().create(params)
            .enqueue(callback)
    }

    fun setUser(user: User) {
        loggedUser = user
    }

    fun getLoggedUser(): User? {
        return loggedUser
    }
}