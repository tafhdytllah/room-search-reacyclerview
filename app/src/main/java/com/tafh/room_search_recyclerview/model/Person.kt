package com.tafh.room_search_recyclerview.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    val name: String,
    val age: Int,
    @Embedded
    val address: Address
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
