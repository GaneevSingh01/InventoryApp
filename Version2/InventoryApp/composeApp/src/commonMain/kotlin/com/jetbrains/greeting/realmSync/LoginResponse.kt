package com.jetbrains.greeting.realmSync

enum class LoginResponse(val displayMessage: String) {
    InvalidCredentials("Invalid Credentials"),
    LoginSuccessful ("Login Successful"),
    NoState("Unknown")
}