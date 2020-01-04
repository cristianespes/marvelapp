package com.cristianespes.domain

import java.util.*

data class Hero(
    val id: Int,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val thumbnail: String?,
    val favorite: Boolean = false
)