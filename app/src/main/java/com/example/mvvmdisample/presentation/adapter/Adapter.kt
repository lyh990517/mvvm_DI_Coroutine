package com.example.mvvmdisample.presentation.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdisample.data.entity.entity
import com.example.mvvmdisample.databinding.ActivityMainBinding
import com.example.mvvmdisample.databinding.ItemTitleBinding

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var DataList: List<entity> = mutableListOf()
    private lateinit var ItemClickListener: (entity) -> Unit

    inner class ViewHolder(
        private val binding: ItemTitleBinding,
        val Listener: (entity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: entity) = with(binding) {
            titleItem.text = data.title
        }

        fun bindView(data: entity) {
            binding.root.setOnClickListener {
                Listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view = ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, ItemClickListener)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bindData(DataList[position])
        holder.bindView(DataList[position])
    }

    override fun getItemCount(): Int {
        return DataList.size
    }

    fun setData(DataList: List<entity>, Listener: (entity) -> Unit) {
        this.DataList = DataList
        this.ItemClickListener = Listener
        notifyDataSetChanged()
    }

}