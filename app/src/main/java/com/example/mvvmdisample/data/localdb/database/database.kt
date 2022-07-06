package com.example.mvvmdisample.data.localdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmdisample.data.entity.entity
import com.example.mvvmdisample.data.localdb.dao.dao

@Database(
    entities = [entity::class],
    version = 1,
    exportSchema = false
)
abstract class database : RoomDatabase(){
    companion object{
        const val DB_NAME = "Database.db"
    }

    abstract fun Dao(): dao
}