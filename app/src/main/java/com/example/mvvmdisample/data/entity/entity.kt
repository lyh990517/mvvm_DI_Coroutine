package com.example.mvvmdisample.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class entity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String
)
