package com.cristianespes.marvelapp.model

import com.cristianespes.marvelapp.model.server.Character
import com.cristianespes.domain.Hero as DomainHero
import com.cristianespes.marvelapp.model.database.Hero as RoomHero

fun DomainHero.toRoomHero(): RoomHero = RoomHero(
        id,
        name,
        description,
        modified,
        thumbnail,
        favorite
    )

fun RoomHero.toDomainHero(): DomainHero = DomainHero(
    id,
    name,
    description,
    modified,
    thumbnail,
    favorite
)

fun Character.toRoomHero() = RoomHero(
    0,
    name,
    description,
    modified,
    thumbnail = thumbnail?.path.plus(".${thumbnail?.extension}"),
    favorite = false
)
