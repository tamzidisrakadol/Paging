package com.example.paging.api

import com.example.paging.utility.Constraints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun getRetrofit(): QuoteApi {
        return Retrofit.Builder()
            .baseUrl(Constraints.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

}