package com.example.myapplication.users

import android.widget.EditText
import com.example.myapplication.BaseForm

class UserNewForm: BaseForm {
    var usernameInput: EditText
    var passwordInput: EditText
    var passwordConfirmationInput: EditText

    constructor(username: EditText, password: EditText, password_confirmation: EditText) {
        this.usernameInput = username
        this.passwordInput = password
        this.passwordConfirmationInput = password_confirmation
    }

    fun isValid(): Boolean {
        if (usernameInput.text == null || usernameInput.text.toString().length < 10)
            errors[usernameInput.id] = "Username error"
        if (passwordInput.text == null || passwordInput.text.toString().length < 6)
            errors[passwordInput.id] = "Password error"
        if (passwordConfirmationInput.text == null || passwordConfirmationInput.text.toString() != passwordInput.text.toString())
            errors[passwordConfirmationInput.id] = "Password confirmation error"
        return errors.isEmpty()
    }

    fun values(): UserCreateAPI {
        return UserCreateAPI(
            usernameInput.text.toString(),
            passwordInput.text.toString(),
            passwordConfirmationInput.text.toString()
        )
    }
}

data class UserCreateAPI(val username: String, val password: String, val password_confirmation: String)
