package com.mongodb.dublinmug_kmm

import CommonFlow
import asCommonFlow
import com.mongodb.dublinmug_kmm.Utils.LoginResponse
import com.mongodb.dublinmug_kmm.models.ProductDataModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.InvalidCredentialsException
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.map
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
            .Builder(user, setOf(ProductDataModel::class))
            .initialSubscriptions(rerunOnOpen = true) { realm ->
                add(realm.query<ProductDataModel>())
            }
            .build()
        realm = Realm.open(config)
    }

    fun saveObject(objectToSave: ProductDataModel) {
        realm.writeBlocking {
            copyToRealm(objectToSave)
        }
    }

    fun deleteObject(objectToDelete: ProductDataModel) {
        realm.writeBlocking {
            val query = query<ProductDataModel>(query = "_id == $0", objectToDelete._id).find().first()
            query.isDeleted = true
        }
    }

    fun getAllActiveProducts(): CommonFlow<List<ProductDataModel>> {
        return realm.query<ProductDataModel>(query = "isDeleted == $0", false)
            .asFlow()
            .map {
                it.list
            }.asCommonFlow()
    }
}
