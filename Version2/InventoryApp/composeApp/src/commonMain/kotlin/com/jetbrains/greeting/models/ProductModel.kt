package com.jetbrains.greeting.models

import com.jetbrains.greeting.realmSync.RealmRepo
import io.realm.kotlin.ext.toRealmDictionary
import io.realm.kotlin.types.RealmUUID

class ProductModel(
    mainViewModel: MainViewModel
){
    private val repo: RealmRepo = mainViewModel.repo
    fun getItemDataLive(): List<ProductDataModel> {
       return repo.getAllActiveProducts()
    }

    fun getProduct(id: String): ProductDataModel? {
        return repo.getObject(id)
    }

    fun deleteProduct(product: ProductDataModel) {
        repo.deleteObject(product)
    }

    fun saveNewProduct(productName: String, productAttribute: Map<String, String>){
        val product = ProductDataModel().apply {
            _id = RealmUUID.random().toString()
            name = productName
            attributes = productAttribute.toRealmDictionary()
        }
        repo.saveObject(product)
    }
}