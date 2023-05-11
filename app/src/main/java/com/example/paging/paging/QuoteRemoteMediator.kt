package com.example.paging.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paging.api.QuoteApi
import com.example.paging.model.QuoteRemoteKeys
import com.example.paging.model.Result
import com.example.paging.storage.QuoteDatabase

@OptIn(ExperimentalPagingApi::class)
class QuoteRemoteMediator(
    private val quoteApi: QuoteApi,
    private val quoteDatabase: QuoteDatabase
) : RemoteMediator<Int, Result>() {

    private val quoteDao = quoteDatabase.quoteDao()
    private val quoteRemoteKeysDao = quoteDatabase.remoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {
        return try {
            val currentPage = when(loadType){

                LoadType.APPEND->{
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nxtPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys!=null
                        )
                    nxtPage
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstTime(state)
                    val prev = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prev
                }

                LoadType.REFRESH->{
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1)?:1
                }
            }
            val response = quoteApi.getQuotes(currentPage)
            val endOfPaginationReached = response.totalPages == currentPage
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            quoteDatabase.withTransaction {

                if (loadType == LoadType.REFRESH){
                    quoteDao.deleteQuotes()
                    quoteRemoteKeysDao.deleteAllRemoteKeys()
                }

                quoteDao.addQuotes(response.results)
                val keys = response.results.map {
                    QuoteRemoteKeys(
                        _id = it.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                quoteRemoteKeysDao.addAllRemoteKeys(keys)
            }
            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Result>): QuoteRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {
            quoteRemoteKeysDao.getRemoteKeys(id = it.id)
        }
    }

    private suspend fun getRemoteKeysForFirstTime(state: PagingState<Int, Result>):QuoteRemoteKeys?{
        return state.pages.firstOrNull{
            it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { quote ->
                quoteRemoteKeysDao.getRemoteKeys(id = quote.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ): QuoteRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                quoteRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

}




