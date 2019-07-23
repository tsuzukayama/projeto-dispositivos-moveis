package com.example.myapplication.rides


object RideDAO {
    private var rides: MutableList<Ride> = ArrayList()
    val instance = this

    init {
        loadTestData()
    }

    private fun loadTestData() {
        var r: Ride

        r = Ride()

        r.departure = "Santo André"
        r.destination = "São Bernardo do Campo"

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