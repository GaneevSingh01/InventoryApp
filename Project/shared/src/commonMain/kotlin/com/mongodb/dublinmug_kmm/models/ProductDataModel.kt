package com.mongodb.dublinmug_kmm.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ProductDataModel : RealmObject {
    @PrimaryKey
    var _id: String = ""
    var name: String = ""
    var attribute: String = ""
}
