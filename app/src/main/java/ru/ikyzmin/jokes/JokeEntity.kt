package ru.ikyzmin.jokes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "jokes")
data class JokeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "joke", defaultValue = "") val joke: String,
    @ColumnInfo(name = "setup", defaultValue = "") val setup: String,
    @ColumnInfo(name = "delivery", defaultValue = "") val delivery: String,
    @ColumnInfo(name = "date_added") val date: LocalDateTime
)
