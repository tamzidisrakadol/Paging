package com.example.paging.api

import com.example.paging.model.QuoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page")page:Int):QuoteModel



}