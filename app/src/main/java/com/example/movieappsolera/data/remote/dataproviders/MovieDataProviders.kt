package com.example.movieappsolera.data.remote.dataproviders

import com.example.movieappsolera.data.local.dao.MovieDao
import com.example.movieappsolera.data.local.entities.MovieEntity
import com.example.movieappsolera.data.remote.services.ApiServiceMovie
import javax.inject.Inject

class MovieDataProviders @Inject constructor(private val apiServiceMovie: ApiServiceMovie){

    @Inject
    lateinit var movieDao: MovieDao

    suspend fun getMovieListPopular(apiKey: String) = apiServiceMovie.getMoviesPopular(apiKey)

    suspend fun getMovieById(movieId: String, apiKey: String) = apiServiceMovie.getMoviesById(movieId, apiKey)

    suspend fun getMovieListFavorites() = movieDao.getAllMovies()

    suspend fun insertMovie(movie : MovieEntity){
        movieDao.insertMovie(movie)
    }
}