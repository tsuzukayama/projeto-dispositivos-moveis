package com.example.myapplication.rides

import com.example.myapplication.users.User

class Ride {
    var driver: User? = null
    var destination: String? = null
    var departure: String? = null
    var capacity: Int? = null
}