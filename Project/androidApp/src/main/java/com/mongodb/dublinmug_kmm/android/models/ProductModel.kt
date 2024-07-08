package com.mongodb.dublinmug_kmm.android.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.mongodb.dublinmug_kmm.RandomUUID
import com.mongodb.dublinmug_kmm.RealmRepo
import com.mongodb.dublinmug_kmm.models.MainViewModel
import com.mongodb.dublinmug_kmm.models.ProductDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class ProductModel(
    mainViewModel: MainViewModel
){
    private val repo: RealmRepo = mainViewModel.repo
    fun getItemDataLive(): LiveData<List<ProductDataModel>> {
        return liveData {
            emitSource(
                repo.getAllActiveProducts().flowOn(Dispatchers.IO).asLiveData(Dispatchers.Main))
        }
    }

    fun getProduct(id: String): ProductDataModel? {
        return repo.getObject(id)
    }

    fun deleteProduct(product: ProductDataModel) {
        repo.deleteObject(product)
    }

    fun saveNewProduct(productName: String, productAttribute: String){
        val product = ProductDataModel().apply {
            _id = RandomUUID().randomId
            name = productName
            attribute = productAttribute
        }
        repo.saveObject(product)
    }
}