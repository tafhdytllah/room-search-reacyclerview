package com.tafh.room_search_recyclerview.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tafh.room_search_recyclerview.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: List<Person>)

    @Query("SELECT * FROM person_table ORDER BY id ASC")
    fun readPerson(): LiveData<List<Person>>

    @Query("SELECT * FROM person_table WHERE name LIKE :query OR streetName LIKE :query")
    fun searchPerson(query: String): Flow<List<Person>>

}