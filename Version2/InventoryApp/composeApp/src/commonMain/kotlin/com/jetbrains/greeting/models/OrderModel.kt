package com.jetbrains.greeting.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class OrderModel : RealmObject {
    @PrimaryKey
    var _id: String = ""
    var source: String = ""
//    var details: Map<String, String> = emptyMap()
}