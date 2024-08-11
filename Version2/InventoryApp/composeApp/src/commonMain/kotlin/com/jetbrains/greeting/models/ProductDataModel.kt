package com.jetbrains.greeting.models

import io.realm.kotlin.ext.realmDictionaryOf
import io.realm.kotlin.types.RealmDictionary
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ProductDataModel : RealmObject {
    @PrimaryKey
    var _id: String = ""
    var name: String = ""
    var attributes: RealmDictionary<String> = realmDictionaryOf()
    var isDeleted: Boolean = false
}

data class ProductAttribute(var name: String = "", var value: String = "") {
    companion object {
        fun toMap(attributes: List<ProductAttribute>): Map<String, String> {
            return attributes.associate { attribute -> attribute.name to attribute.value }
        }
    }
}