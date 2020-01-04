package com.cristianespes.marvelapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Hero(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val thumbnail: String?,
    val favorite: Boolean = false
)
