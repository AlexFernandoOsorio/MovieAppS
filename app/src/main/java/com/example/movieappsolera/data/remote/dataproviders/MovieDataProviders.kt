package com.example.movieappsolera.data.remote.dataproviders

import com.example.movieappsolera.data.remote.services.ApiServiceMovie
import javax.inject.Inject

class MovieDataProviders @Inject constructor(private val apiServiceMovie: ApiServiceMovie){


    suspend fun getMovieListPopular(apiKey: String) = apiServiceMovie.getMoviesPopular(apiKey)
}