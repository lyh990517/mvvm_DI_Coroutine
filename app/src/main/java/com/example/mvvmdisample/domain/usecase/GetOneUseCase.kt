package com.example.mvvmdisample.domain.usecase

import com.example.mvvmdisample.data.entity.entity
import com.example.mvvmdisample.data.repository.repository

internal class GetOneUseCase(
    private val repository: repository
) {
    suspend operator fun invoke(id: Long): entity? {
        return repository.getOne(id)
    }
}