package com.example.paging.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class QuoteRemoteKeys (
    val _id:String,
    val prevPage:Int?,
    val nextPage:Int?
)


