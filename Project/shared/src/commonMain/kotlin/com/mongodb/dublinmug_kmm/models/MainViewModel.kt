package com.mongodb.dublinmug_kmm.models

import com.mongodb.dublinmug_kmm.RealmRepo
import com.mongodb.dublinmug_kmm.Utils.LoginResponse

class MainViewModel {

    val repo = RealmRepo()

    fun login(email: String, password: String): LoginResponse{
        return repo.login(email, password)
    }
}