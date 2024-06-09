package com.mongodb.dublinmug_kmm.Utils

enum class LoginResponse(val displayMessage: String) {
    InvalidCredentials("Invalid Credentials"),
    LoginSuccessful ("Login Successful"),
    NoState("Unknown")
}