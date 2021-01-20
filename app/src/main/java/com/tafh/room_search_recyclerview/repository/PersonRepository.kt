package com.tafh.room_search_recyclerview.repository

import androidx.lifecycle.LiveData
import com.tafh.room_search_recyclerview.data.local.dao.PersonDao
import com.tafh.room_search_recyclerview.model.Person
import kotlinx.coroutines.flow.Flow

class PersonRepository(private val personDao: PersonDao) {

    val readPerson: LiveData<List<Person>> = personDao.readPerson()

    suspend fun insertPerson(person: List<Person>) = personDao.insertPerson(person)

    fun searchPerson(query: String): Flow<List<Person>> = personDao.searchPerson(query)

}