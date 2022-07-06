package com.example.mvvmdisample.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.example.mvvmdisample.data.localdb.database.database
import com.example.mvvmdisample.data.repository.repository
import com.example.mvvmdisample.data.repository.repositoryImpl
import com.example.mvvmdisample.domain.usecase.DeleteOneUseCase
import com.example.mvvmdisample.domain.usecase.GetAllUseCase
import com.example.mvvmdisample.domain.usecase.GetOneUseCase
import com.example.mvvmdisample.domain.usecase.InsertOneUseCase
import com.example.mvvmdisample.presentation.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

internal val appModule = module{
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    single<repository> { repositoryImpl(get(),get()) }

    single { provideDB(androidApplication()) }
    single { provideDao(get()) }

    factory { DeleteOneUseCase(get()) }
    factory { GetAllUseCase(get()) }
    factory { GetOneUseCase(get()) }
    factory { InsertOneUseCase(get()) }

    viewModel { MainViewModel(get(),get(),get(),get()) }
}
internal fun provideDB(context: Context): database =
    Room.databaseBuilder(context, database::class.java, database.DB_NAME).build()

internal fun provideDao(database: database) = database.Dao()