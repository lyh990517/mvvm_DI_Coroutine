package com.example.mvvmdisample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdisample.databinding.ActivityMainBinding
import com.example.mvvmdisample.presentation.BaseActivity
import com.example.mvvmdisample.presentation.adapter.Adapter
import com.example.mvvmdisample.presentation.main.DataState
import com.example.mvvmdisample.presentation.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

internal class MainActivity : BaseActivity<MainViewModel>(),CoroutineScope {
    private lateinit var binding: ActivityMainBinding

    private val adapter = Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
    override val viewModel: MainViewModel by viewModel()

    override fun observeData() {
        viewModel.LiveData.observe(this){
            when(it){
                is DataState.Uninitialized -> {
                    initViews(binding)
                }
                is DataState.Loading -> {
                    handleLoading()
                }
                is DataState.SuccessOne -> {
                    handleSuccessOne(it)
                }
                is DataState.SuccessAll -> {
                    handleSuccessAll(it)
                }
                is DataState.Error -> {

                }
                is DataState.Delete -> {
                    handleDeleteOne()
                }
            }
        }
    }

    private fun handleSuccessAll(state: DataState.SuccessAll) = with(binding){
        refresh.isEnabled = state.Data.isNotEmpty()
        refresh.isRefreshing = false

        if(state.Data.isEmpty()){
            recycler.isGone = true
        } else{
            recycler.isGone = false
            empty.isGone = true
            adapter.setData(
                state.Data,
                Listener = {
                    viewModel.deleteOne(it.id)
                }
            )
        }
    }
    private fun handleDeleteOne() = with(binding){
        refresh.isRefreshing = false
        viewModel.fetchData()
        Toast.makeText(this@MainActivity,"삭제되었습니다.",Toast.LENGTH_SHORT).show()
    }
    private fun handleSuccessOne(state: DataState.SuccessOne) = with(binding){
        refresh.isEnabled = true
        refresh.isRefreshing = false
        viewModel.fetchData()

    }

    private fun handleLoading() = with(binding){
        refresh.isRefreshing = true
    }

    private fun initViews(binding: ActivityMainBinding) = with(binding){
        recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler.adapter = adapter
        refresh.setOnRefreshListener {
            viewModel.fetchData()
        }

        add.setOnClickListener{
            viewModel.insertOne(title.text.toString())
        }
    }
}