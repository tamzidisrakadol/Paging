package com.example.paging.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging.model.QuoteRemoteKeys


@Dao
interface RemoteDao {

    @Query("SELECT * FROM QuoteRemoteKeys WHERE _id = :id")
    suspend fun getRemoteKeys(id:String):QuoteRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<QuoteRemoteKeys>)

    @Query("DELETE FROM QuoteRemoteKeys")
    suspend fun deleteAllRemoteKeys()


}