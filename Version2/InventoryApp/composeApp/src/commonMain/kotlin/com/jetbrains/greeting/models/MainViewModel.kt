package com.jetbrains.greeting.models

import com.jetbrains.greeting.realmSync.LoginResponse
import com.jetbrains.greeting.realmSync.RealmRepo

class MainViewModel {

    val repo = RealmRepo()

    fun login(email: String, password: String): LoginResponse {
        return repo.login(email, password)
    }
}