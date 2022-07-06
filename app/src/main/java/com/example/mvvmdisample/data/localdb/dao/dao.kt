package com.example.mvvmdisample.data.localdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmdisample.data.entity.entity

@Dao
interface dao {
    @Query("SELECT * FROM entity")
    suspend fun getAll(): List<entity>

    @Query("SELECT * FROM entity WHERE id =:id")
    suspend fun getOne(id:Long): entity

    @Query("DELETE FROM entity WHERE id= :id")
    suspend fun delete(id: Long)

    @Insert
    suspend fun insert(entity: entity): Long
}