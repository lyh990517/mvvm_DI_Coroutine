package com.example.mvvmdisample.domain.usecase

import com.example.mvvmdisample.data.entity.entity
import com.example.mvvmdisample.data.repository.repository

internal class InsertOneUseCase(
    private val repository: repository
) {
    suspend operator fun invoke(entity: entity): Long {
        return repository.insertItem(entity)
    }
}