package com.example.myapplication.rides.model

import com.example.myapplication.users.User

class Ride (

    var id: Int,
    var driver_id: Int,
    var driver: User,
    var destination: String,
    var origin: String,
    var capacity: Int,
    var created_at: String,
    var updated_at: String
)