package com.cristianespes.marvelapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Hero::class], version = 1)
@TypeConverters(com.cristianespes.marvelapp.data.database.TypeConverters::class)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}
