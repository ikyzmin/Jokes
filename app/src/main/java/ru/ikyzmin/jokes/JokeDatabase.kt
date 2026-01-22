package ru.ikyzmin.jokes

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [JokeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class JokesDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokesDao
}
