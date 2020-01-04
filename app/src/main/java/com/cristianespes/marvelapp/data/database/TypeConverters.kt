package com.cristianespes.marvelapp.data.database

import androidx.room.TypeConverter
import java.util.*

class TypeConverters {
    @TypeConverter
    fun fromLongToDate(input: Long?): Date? = Date(input ?: 0)

    @TypeConverter
    fun fromDateToLong(input: Date?): Long? = input?.time
}