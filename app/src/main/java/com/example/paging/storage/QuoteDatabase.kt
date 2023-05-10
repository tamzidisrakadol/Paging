package com.example.paging.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.paging.model.QuoteRemoteKeys
import com.example.paging.model.Result


@Database(entities = [Result::class, QuoteRemoteKeys::class], version = 1)
abstract class QuoteDatabase :RoomDatabase(){

    abstract fun quoteDao(): QuoteDao
    abstract fun remoteKeysDao(): RemoteDao

    companion object {

        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,
                            QuoteDatabase::class.java,
                            "QUOTEDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}