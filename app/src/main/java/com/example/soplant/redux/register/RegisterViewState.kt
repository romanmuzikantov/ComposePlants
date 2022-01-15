package com.example.soplant.redux.register

import com.example.soplant.redux.State
import javax.inject.Inject

data class RegisterViewState(
    val isSigningUp: Boolean,
    val signUpSuccessful: Boolean,
    val signUpFailed: Boolean,
    val username: String,
    val email: String,
    val password: String,
    val tosChecked: Boolean,
    val canSignUp: Boolean,
    val isEmailValid: Boolean,
    val isPasswordValid: Boolean,
    val formValidated: Boolean,
    val errorCode: String
): State {
    @Inject
    constructor(): this(false, false, false, "", "", "", false, false, true, true, false, "")
}