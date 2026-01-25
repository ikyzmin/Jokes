package ru.ikyzmin.jokes.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokesDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun addJoke(joke: JokeEntity)

    @Query("SELECT * FROM jokes")
    suspend fun getJokes(): List<JokeEntity>

    @Query("SELECT * FROM jokes WHERE id = :id")
    suspend fun getJokes(id: Int): JokeEntity
}
