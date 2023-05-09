package com.example.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.databinding.LoaderAdapterBinding

class LoaderAdapter: LoadStateAdapter<LoaderAdapter.LoadViewHolder>() {


    class LoadViewHolder(val binding: LoaderAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressbar.isVisible = loadState is LoadState.Loading
            binding.loaderTV.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        val binding = LoaderAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadViewHolder(binding)
    }
}