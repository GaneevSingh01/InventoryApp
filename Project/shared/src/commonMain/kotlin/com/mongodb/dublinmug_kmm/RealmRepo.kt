package com.mongodb.dublinmug_kmm

import com.mongodb.dublinmug_kmm.Utils.LoginResponse
import com.mongodb.dublinmug_kmm.models.ItemDataModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.InvalidCredentialsException
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.runBlocking


// p: itguy2022
class RealmRepo {

    lateinit var realm: Realm
    lateinit var user: User

    private val appServiceInstance by lazy {
        // If logs are on app level then it set for everything ..
        val configuration =
            AppConfiguration.Builder("application-0-rqfuqzq").log(LogLevel.ALL).build()
        App.create(configuration)
    }

    fun login(email: String, password: String): LoginResponse {
        val credentials = Credentials.emailPassword(email, password)

        try{
            user = runBlocking { appServiceInstance.login(credentials) }
            setupRealmSync()
            return LoginResponse.LoginSuccessful
        } catch (invalidCredentialsException : InvalidCredentialsException) {
            return LoginResponse.InvalidCredentials
        }
    }

    private fun setupRealmSync() {
        val config = SyncConfiguration
            .Builder(user, setOf(ItemDataModel::class))
            .initialSubscriptions(rerunOnOpen = true) { realm ->
                add(realm.query<ItemDataModel>())
            }
            .build()
        realm = Realm.open(config)
    }

    suspend fun saveInfo(nameToSave: String) {
        val info = ItemDataModel().apply {
            _id = RandomUUID().randomId
            name = nameToSave
        }
        realm.write {
            copyToRealm(info)
        }
    }

//    suspend fun editInfo(queryInfo: QueryInfo) {
//        realm.write {
//            copyToRealm(queryInfo, updatePolicy = UpdatePolicy.ALL)
//        }
//    }
//
//    fun getAllData(): CommonFlow<List<QueryInfo>> {
//        return realm.query<QueryInfo>()
//            .sort(property = "timestamp", sortOrder = Sort.DESCENDING)
//            .asFlow()
//            .map {
//                it.list
//            }.asCommonFlow()
//    }
//
//    suspend fun dummyData() {
//        for (i in 1..100) {
//            val info = QueryInfo().apply {
//                _id = RandomUUID().randomId
//                queries = "random"
//            }
//            realm.write {
//                copyToRealm(info)
//            }
//        }
//    }
//
//    suspend fun removeDummyData() {
//        realm.write {
//            val items = query<QueryInfo>("queries = $0", "random").find()
//            delete(items)
//        }
//    }
}
