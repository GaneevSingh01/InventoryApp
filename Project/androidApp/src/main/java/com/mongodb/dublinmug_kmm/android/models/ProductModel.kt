package com.mongodb.dublinmug_kmm.android.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.mongodb.dublinmug_kmm.RandomUUID
import com.mongodb.dublinmug_kmm.RealmRepo
import com.mongodb.dublinmug_kmm.models.MainViewModel
import com.mongodb.dublinmug_kmm.models.ProductDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProductModel(
    mainViewModel: MainViewModel
){
    private val repo: RealmRepo = mainViewModel.repo
    fun getItemDataLive(): LiveData<List<ProductDataModel>> {
        return liveData {
            emitSource(
                repo.getAllItemData().flowOn(Dispatchers.IO).asLiveData(Dispatchers.Main))
        }
    }

    suspend fun saveNewProduct(productName: String, productAttribute: String){
        val product = ProductDataModel().apply {
            _id = RandomUUID().randomId
            name = productName
            attribute = productAttribute
        }

        coroutineScope {
            launch {
                repo.saveObject(product)
            }
        }
    }
}