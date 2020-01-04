package com.cristianespes.marvelapp.model.database

import androidx.room.*

@Dao
interface MarvelDao {
    @Query("SELECT * FROM Hero")
    fun getAllHeroes(): List<Hero>

    @Query("SELECT * FROM Hero WHERE id = :id")
    fun findHeroById(id: Int): Hero

    @Query("SELECT COUNT(id) FROM Hero")
    fun heroesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHeroes(heroes: List<Hero>)

    @Update
    fun updateHero(hero: Hero)
}