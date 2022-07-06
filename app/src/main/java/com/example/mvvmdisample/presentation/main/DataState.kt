package com.example.mvvmdisample.presentation.main

import com.example.mvvmdisample.data.entity.entity

sealed class DataState{

    object Uninitialized: DataState()

    object Loading: DataState()

    data class SuccessAll(
        val Data : List<entity>
    ): DataState()
    data class SuccessOne(
        val Data : entity
    ): DataState()
    object Error: DataState()

    object Delete: DataState()
}
