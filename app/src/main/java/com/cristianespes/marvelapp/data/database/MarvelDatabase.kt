package com.cristianespes.marvelapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Hero::class], version = 1)
@TypeConverters(com.cristianespes.marvelapp.data.database.TypeConverters::class)
abstract class MarvelDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "marvel-db"
        ).build()
    }

    abstract fun marvelDao(): MarvelDao
}
