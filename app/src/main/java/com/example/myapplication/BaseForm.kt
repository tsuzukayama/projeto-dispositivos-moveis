package com.example.myapplication

abstract class BaseForm {
    var errors: HashMap<Int, String> = HashMap()

    abstract fun isValid(): Boolean
}