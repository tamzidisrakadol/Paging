package com.example.paging.repository
import androidx.paging.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging.api.QuoteApi
import com.example.paging.paging.QuotePagingSource
import com.example.paging.paging.QuoteRemoteMediator
import com.example.paging.storage.QuoteDatabase

@OptIn(ExperimentalPagingApi::class)
class QuoteRepository(private val api: QuoteApi, private val quoteDatabase: QuoteDatabase) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = QuoteRemoteMediator(api,quoteDatabase),
        pagingSourceFactory = { quoteDatabase.quoteDao().getQuotes() }
    ).liveData

}