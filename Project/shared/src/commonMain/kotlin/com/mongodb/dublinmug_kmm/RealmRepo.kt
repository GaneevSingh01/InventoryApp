package com.mongodb.dublinmug_kmm

import CommonFlow
import asCommonFlow
import com.mongodb.dublinmug_kmm.Utils.LoginResponse
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.internal.interop.sync.Response
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.InvalidCredentialsException
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

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
            .Builder(user, setOf(QueryInfo::class))
            .initialSubscriptions { realm ->
                // only can write data, which cover in initialSubscriptions
                add(
                    query = realm.query<QueryInfo>(),
                    name = "subscription name",
                    updateExisting = true
                )
            }
            .build()
        realm = Realm.open(config)
    }

    suspend fun saveInfo(query: String) {
        val info = QueryInfo().apply {
            _id = RandomUUID().randomId
            queries = query
        }
        realm.write {
            copyToRealm(info)
        }
    }

    suspend fun editInfo(queryInfo: QueryInfo) {
        realm.write {
            copyToRealm(queryInfo, updatePolicy = UpdatePolicy.ALL)
        }
    }

    fun getAllData(): CommonFlow<List<QueryInfo>> {
        return realm.query<QueryInfo>()
            .sort(property = "timestamp", sortOrder = Sort.DESCENDING)
            .asFlow()
            .map {
                it.list
            }.asCommonFlow()
    }

    suspend fun dummyData() {
        for (i in 1..100) {
            val info = QueryInfo().apply {
                _id = RandomUUID().randomId
                queries = "random"
            }
            realm.write {
                copyToRealm(info)
            }
        }
    }

    suspend fun removeDummyData() {
        realm.write {
            val items = query<QueryInfo>("queries = $0", "random").find()
            delete(items)
        }
    }
}
