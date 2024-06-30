package com.mongodb.dublinmug_kmm.models

import com.mongodb.dublinmug_kmm.RealmRepo
import com.mongodb.dublinmug_kmm.Utils.LoginResponse

class MainViewModel {

    private val repo = RealmRepo()
//    val queries: LiveData<List<QueryInfo>> = liveData {
//        emitSource(repo.getAllData().flowOn(Dispatchers.IO).asLiveData(Dispatchers.Main))
//    }

    fun login(email: String, password: String): LoginResponse{
        return repo.login(email, password)
    }

    suspend fun onSendClick(string: String) {
//        coroutineScope {
//            if (query._id.isBlank()) {
                repo.saveInfo(string)
//            } else {
//                repo.editInfo(query)
//            }
//        }
    }
}