package com.example.data.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class PageDB: RealmObject() {
    @PrimaryKey
    var id = 0
    var page: Int = 0
}