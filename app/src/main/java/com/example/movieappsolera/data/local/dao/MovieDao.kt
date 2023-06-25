package com.example.movieappsolera.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappsolera.data.local.entities.MovieEntity

@Dao
interface MovieDao {


    @Query("SELECT * FROM table_movies ORDER BY tm_release_date DESC")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

}