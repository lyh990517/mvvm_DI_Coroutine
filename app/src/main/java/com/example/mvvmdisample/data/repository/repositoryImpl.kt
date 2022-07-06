package com.example.mvvmdisample.data.repository

import com.example.mvvmdisample.data.entity.entity
import com.example.mvvmdisample.data.localdb.dao.dao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class repositoryImpl(
    private val Dao : dao,
    private val ioDispatcher: CoroutineDispatcher
) : repository {
    override suspend fun getAll(): List<entity> = withContext(ioDispatcher) {
        Dao.getAll()
    }

    override suspend fun getOne(id: Long): entity = withContext(ioDispatcher) {
        Dao.getOne(id)
    }

    override suspend fun insertItem(entity: entity): Long = withContext(ioDispatcher) {
        Dao.insert(entity)
    }

    override suspend fun deleteItem(id: Long) = withContext(ioDispatcher) {
        Dao.delete(id)
    }
}