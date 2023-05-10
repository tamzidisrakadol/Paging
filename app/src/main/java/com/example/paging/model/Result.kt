package com.example.paging.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "QuoteTable")
data class Result(

    @SerializedName("author")
    val author: String,
    @SerializedName("authorSlug")
    val authorSlug: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("dateAdded")
    val dateAdded: String,
    @SerializedName("dateModified")
    val dateModified: String,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    val id: String,


    @SerializedName("length")
    val length: Int,
    @SerializedName("tags")
    val tags: List<String>
)