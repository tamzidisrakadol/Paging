package com.example.paging.repository
import androidx.paging.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging.api.QuoteApi
import com.example.paging.paging.QuotePagingSource

class QuoteRepository(private val api: QuoteApi) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { QuotePagingSource(api) }
    ).liveData

}