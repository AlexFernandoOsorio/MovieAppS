package com.example.movieappsolera.domain.repositories

import com.example.movieappsolera.domain.model.MovieModel

interface MovieRepository {


    suspend fun getMovieListPopularFromRemote(apiKey: String):List<MovieModel>

}