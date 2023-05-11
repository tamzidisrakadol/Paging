package com.example.paging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.paging.repository.QuoteRepository
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class QuoteViewModel(private val repository: QuoteRepository):ViewModel() {

    val list = repository.getQuotes().cachedIn(viewModelScope)
}