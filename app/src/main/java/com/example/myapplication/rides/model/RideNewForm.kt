package com.example.myapplication.rides.model

import android.widget.EditText
import com.example.myapplication.BaseForm

class RideNewForm(
    var driverId: Int,
    var capacity: EditText,
    var origin: String,
    var destination: String
): BaseForm(){

    override fun isValid(): Boolean {
        errors = HashMap()
        if (capacity.text.toString().length <= 0)
            errors[capacity.id] = "Capacity error"
        return errors.isEmpty()
    }

    fun values(): RideService.RideCreateAPI {
        return RideService.RideCreateAPI(
            driverId,
            origin,
            destination,
            capacity.text.toString().toInt()
        )
    }
}