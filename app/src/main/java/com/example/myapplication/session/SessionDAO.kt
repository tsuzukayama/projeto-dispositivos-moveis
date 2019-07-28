package com.example.myapplication.session

import com.example.myapplication.RetrofitInitializer
import retrofit2.Callback

object SessionDAO {
    fun add(params: SessionService.SessionCreateAPI, callback: Callback<Any>) {
        RetrofitInitializer().sessionService().create(params)
            .enqueue(callback)
    }
}