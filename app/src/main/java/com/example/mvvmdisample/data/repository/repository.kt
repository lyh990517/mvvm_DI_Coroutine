package com.example.mvvmdisample.data.repository

import com.example.mvvmdisample.data.entity.entity

interface repository {
    suspend fun getAll(): List<entity>

    suspend fun getOne(id: Long): entity

    suspend fun insertItem(entity: entity): Long

    suspend fun deleteItem(id: Long)
}