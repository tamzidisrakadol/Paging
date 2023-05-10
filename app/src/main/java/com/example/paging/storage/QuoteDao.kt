package com.example.paging.storage

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging.model.Result


@Dao
interface QuoteDao {

    @Query("SELECT * FROM QuoteTable")
    fun getQuotes():PagingSource<Int,Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes:List<Result>)

    @Query("DELETE FROM QuoteTable")
    suspend fun deleteQuotes()
}