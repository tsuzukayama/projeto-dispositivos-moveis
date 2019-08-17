package com.example.myapplication.rides.model

import android.widget.EditText
import com.example.myapplication.BaseForm

class RideNewForm(
    val driverId: Int,
    val capacity: EditText,
    val origin: EditText,
    val destination: EditText
): BaseForm(){

    override fun isValid(): Boolean {
        errors = HashMap()
        if (capacity.text.toString().length <= 0)
            errors[capacity.id] = "Capacity error"
        if (origin.text.toString().length <= 0)
            errors[origin.id] = "Origin error"
        if (destination.text.toString().length <= 0)
            errors[destination.id] = "Destination error"
        return errors.isEmpty()
    }

    fun values(): RideService.RideCreateAPI {
        return RideService.RideCreateAPI(
            driverId,
            origin.text.toString(),
            destination.text.toString(),
            capacity.text.toString().toInt()
        )
    }
}