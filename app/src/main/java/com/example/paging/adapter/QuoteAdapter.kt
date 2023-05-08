package com.example.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R
import com.example.paging.databinding.ListItemBinding
import com.example.paging.model.Result

class QuoteAdapter:PagingDataAdapter<Result,QuoteAdapter.QuoteViewHolder>(Comparator) {

    class QuoteViewHolder(val itemBinding: ListItemBinding):RecyclerView.ViewHolder(itemBinding.root){

    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
       val item = getItem(position)
        if (item!=null){
            holder.itemBinding.quoteTV.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
         val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuoteViewHolder(itemBinding)
    }


    companion object{
        private val Comparator = object : DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem==newItem
            }

        }
    }

}