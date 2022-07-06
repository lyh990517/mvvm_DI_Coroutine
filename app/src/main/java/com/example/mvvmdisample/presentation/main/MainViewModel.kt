package com.example.mvvmdisample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmdisample.data.entity.entity
import com.example.mvvmdisample.domain.usecase.DeleteOneUseCase
import com.example.mvvmdisample.domain.usecase.GetAllUseCase
import com.example.mvvmdisample.domain.usecase.GetOneUseCase
import com.example.mvvmdisample.domain.usecase.InsertOneUseCase
import com.example.mvvmdisample.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

internal class MainViewModel(
    private val getAllUseCase: GetAllUseCase,
    private val getOneUseCase: GetOneUseCase,
    private val insertOneUseCase: InsertOneUseCase,
    private val deleteOneUseCase: DeleteOneUseCase
) : BaseViewModel(){

    private var _LiveData = MutableLiveData<DataState>(DataState.Uninitialized)
    val LiveData:LiveData<DataState> = _LiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _LiveData.postValue(DataState.Loading)
        _LiveData.postValue(DataState.SuccessAll(getAllUseCase()))
    }

    fun getOne(id:Long) = viewModelScope.launch {
        _LiveData.postValue(DataState.Loading)
        try{
            getOneUseCase(id)?.let {
                _LiveData.postValue(DataState.SuccessOne(it))
            } ?: kotlin.run {
                _LiveData.postValue(DataState.Error)
            }
        }catch (e:Exception){
            e.printStackTrace()
            _LiveData.postValue(DataState.Error)
        }
    }
    fun deleteOne(id: Long) = viewModelScope.launch {
        _LiveData.postValue(DataState.Loading)
        try{
            deleteOneUseCase(id)
            _LiveData.postValue(DataState.Delete)
        }catch (e:Exception){
            e.printStackTrace()
            _LiveData.postValue(DataState.Error)
        }
    }

    fun insertOne(title: String) = viewModelScope.launch {
        _LiveData.postValue(DataState.Loading)
        try{
            val entity = entity(title = title)
            val id = insertOneUseCase(entity)
            _LiveData.postValue(DataState.SuccessOne(entity))
        }catch (e:Exception){
            e.printStackTrace()
            _LiveData.postValue(DataState.Error)
        }
    }
}