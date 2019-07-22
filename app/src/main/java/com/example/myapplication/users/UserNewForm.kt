package com.example.myapplication.users

import android.widget.EditText
import com.example.myapplication.BaseForm

class UserNewForm(
    var usernameInput: EditText,
    var passwordInput: EditText,
    var passwordConfirmationInput: EditText
): BaseForm(){

    override fun isValid(): Boolean {
        errors = HashMap()
        if (usernameInput.text == null || usernameInput.text.toString().length < 3)
            errors[usernameInput.id] = "Username error"
        if (passwordInput.text == null || passwordInput.text.toString().length < 6)
            errors[passwordInput.id] = "Password error"
        if (passwordConfirmationInput.text == null || passwordConfirmationInput.text.toString() != passwordInput.text.toString())
            errors[passwordConfirmationInput.id] = "Password confirmation error"
        return errors.isEmpty()
    }

    fun values(): UserService.UserCreateAPI {
        return UserService.UserCreateAPI(
            usernameInput.text.toString(),
            passwordInput.text.toString(),
            passwordConfirmationInput.text.toString()
        )
    }
}