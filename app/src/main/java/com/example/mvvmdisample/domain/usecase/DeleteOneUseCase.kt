package com.example.mvvmdisample.domain.usecase

import com.example.mvvmdisample.data.repository.repository

internal class DeleteOneUseCase(
    private val repository: repository
) {
    suspend operator fun invoke(id: Long){
        return repository.deleteItem(id)
    }
}