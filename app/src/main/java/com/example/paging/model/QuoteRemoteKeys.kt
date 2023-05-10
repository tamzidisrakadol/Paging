package com.example.paging.model

import androidx.room.Entity


@Entity
data class QuoteRemoteKeys (
    val _id:String,
    val prevPage:Int?,
    val nextPage:Int?
)


