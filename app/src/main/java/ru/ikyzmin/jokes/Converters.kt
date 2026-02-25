package ru.ikyzmin.jokes

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromString(value: String): LocalDateTime? {
        return value.let { LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime): String {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }
}
