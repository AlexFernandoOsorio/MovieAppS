package com.example.movieappsolera.data.repositories

import com.example.movieappsolera.data.remote.dataproviders.MovieDataProviders
import com.example.movieappsolera.data.remote.mappers.toDomainMovieList
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryimpl @Inject constructor(private val movieDataProviders : MovieDataProviders)  : MovieRepository {


    override suspend fun getMovieListPopularFromRemote(apiKey: String): List<MovieModel> {
        return movieDataProviders.getMovieListPopular(apiKey).toDomainMovieList()
    }

}