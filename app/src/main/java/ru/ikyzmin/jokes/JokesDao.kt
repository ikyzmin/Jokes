package ru.ikyzmin.jokes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addJoke(joke: JokeEntity)

    @Query("SELECT * FROM jokes")
    fun getJokes(): List<JokeEntity>
}
