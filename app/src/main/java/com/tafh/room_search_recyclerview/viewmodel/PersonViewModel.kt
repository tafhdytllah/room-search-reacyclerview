package com.tafh.room_search_recyclerview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tafh.room_search_recyclerview.data.local.db.PersonDatabase
import com.tafh.room_search_recyclerview.model.Person
import com.tafh.room_search_recyclerview.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val personDao = PersonDatabase.getDatabase(application).personDao()
    private val repository = PersonRepository(personDao)

    val readPerson: LiveData<List<Person>> = repository.readPerson

    fun insertPerson(person: List<Person>) = viewModelScope.launch(Dispatchers.IO) { repository.insertPerson(person) }

    fun searchPerson(query: String): LiveData<List<Person>> = repository.searchPerson(query).asLiveData()

}