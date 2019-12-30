package com.cristianespes.marvelapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hero(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val description: String?,
    val modified: Long?,
    val thumbnail: String?
)
