package com.mongodb.dublinmug_kmm.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ItemDataModel : RealmObject {
    @PrimaryKey
    var _id: String = ""
    var name: String = ""
//    var details: Map<String, String> = emptyMap()

}
class ItemModel(val mainViewModel: MainViewModel){
    suspend fun sendToDB(name: String){
        mainViewModel.onSendClick(name)
    }
}