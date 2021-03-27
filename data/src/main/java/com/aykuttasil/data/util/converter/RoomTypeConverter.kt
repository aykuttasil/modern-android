package com.aykuttasil.data.util.converter

import androidx.room.TypeConverter
import java.util.*

class RoomTypeConverter {

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(long: Long): Date {
        return Date(long)
    }
}
