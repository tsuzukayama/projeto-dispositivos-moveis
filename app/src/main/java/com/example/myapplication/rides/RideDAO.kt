package com.example.myapplication.rides

import com.example.myapplication.users.User


object RideDAO {
    private var rides: MutableList<Ride> = ArrayList()

    init {
        loadTestData()
    }

    private fun loadTestData() {
        var r: Ride
        var u: User

        r = Ride()
        u = User()

        r.departure = "Santo André"
        r.destination = "São Bernardo do Campo"
        r.capacity = 4

        u.name = "John Doe"
        u.username = "john.doe"

        r.driver = u

        rides.add(r)
    }

    fun add(ride: Ride) {
        rides.add(ride)
    }

    fun size(): Int {
        return rides.size
    }

    fun getItemAt(pos: Int): Ride {
        return rides[pos]
    }
}