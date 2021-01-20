package com.tafh.room_search_recyclerview.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tafh.room_search_recyclerview.data.local.dao.PersonDao
import com.tafh.room_search_recyclerview.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PersonDatabase::class.java,
                "person_db"
            ).build()
    }
}