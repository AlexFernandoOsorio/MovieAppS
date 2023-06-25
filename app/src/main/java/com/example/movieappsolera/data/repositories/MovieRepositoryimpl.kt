package com.example.movieappsolera.data.repositories

import com.example.movieappsolera.data.local.dao.MovieDao
import com.example.movieappsolera.data.local.entities.MovieEntity
import com.example.movieappsolera.data.remote.dataproviders.MovieDataProviders
import com.example.movieappsolera.data.remote.mappers.toDomainMovieDetail
import com.example.movieappsolera.data.remote.mappers.toDomainMovieList
import com.example.movieappsolera.domain.model.MovieDetailModel
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryimpl @Inject constructor(private val movieDataProviders : MovieDataProviders)  : MovieRepository {


    override suspend fun getMovieListPopularFromRemote(apiKey: String): List<MovieModel> {
        return movieDataProviders.getMovieListPopular(apiKey).toDomainMovieList()
    }

    override suspend fun getMovieByIdFromRemote(movieId: String, apiKey: String): MovieDetailModel {
        return movieDataProviders.getMovieById(movieId, apiKey).toDomainMovieDetail()
    }


    override suspend fun getMovieListFromLocal(): List<MovieDetailModel> {
        val response : List<MovieEntity> = movieDataProviders.getMovieListFavorites()

        return response.map {
            MovieDetailModel(
                id = it.id,
                title = it.name,
                image = it.image,
                description = it.description,
                popularity = it.popularity.toDouble(),
                release_date = it.release_date,
                genre_ids = it.genre.split(",").map { it }
            )
        }
    }

    override suspend fun insertMovieToLocal(movie : MovieDetailModel){
        movieDataProviders.insertMovie(MovieEntity(
            id = movie.id,
            name = movie.title,
            image = movie.image,
            description = movie.description,
            popularity = movie.popularity.toString(),
            release_date = movie.release_date,
            genre = movie.genre_ids.joinToString(","),
            isFavorite = true
        ))
    }
}