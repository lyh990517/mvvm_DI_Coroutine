package com.example.mvvmdisample.domain.usecase

import com.example.mvvmdisample.data.entity.entity
import com.example.mvvmdisample.data.repository.repository

internal class GetAllUseCase(
    private val repository: repository
) {

    suspend operator fun invoke(): List<entity>{
        return repository.getAll()
    }
}