package com.cristianespes.marvelapp.model.database

import androidx.room.*

@Dao
interface MarvelDao {
    @Query("SELECT * FROM Hero")
    fun getAll(): List<Hero>

    @Query("SELECT * FROM Hero WHERE id = :id")
    fun findById(id: Int): Hero

    @Query("SELECT COUNT(id) FROM Hero")
    fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(heroes: List<Hero>)

    @Update
    fun updateMovie(hero: Hero)
}