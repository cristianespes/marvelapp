package com.cristianespes.marvelapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Hero::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}