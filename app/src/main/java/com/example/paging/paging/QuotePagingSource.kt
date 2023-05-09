package com.example.paging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.api.QuoteApi
import com.example.paging.model.Result

class QuotePagingSource(private val quoteApi: QuoteApi):PagingSource<Int,Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key?:1
            val response = quoteApi.getQuotes(position)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position==1) null else position - 1,
                nextKey = if (position==response.totalPages) null else position + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}