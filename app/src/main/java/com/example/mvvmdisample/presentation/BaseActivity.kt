package com.example.mvvmdisample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Job

internal abstract class BaseActivity<vm: BaseViewModel>: AppCompatActivity() {
    abstract val viewModel: vm

    private lateinit var fetchJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchJob = viewModel.fetchData()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroy() {
        if(fetchJob.isActive){
            fetchJob.cancel()
        }
        super.onDestroy()
    }
}