package com.example.movieappsolera.data.local.dao

import androidx.room.*
import com.example.movieappsolera.data.local.entities.MovieEntity

@Dao
interface MovieDao {


    @Query("SELECT * FROM table_movies ORDER BY tm_release_date DESC")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

}