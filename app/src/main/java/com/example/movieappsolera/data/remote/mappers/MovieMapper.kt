package com.example.movieappsolera.data.remote.mappers

import com.example.movieappsolera.data.remote.models.movies.MovieListResponse
import com.example.movieappsolera.domain.model.MovieModel

fun MovieListResponse.toDomainMovieList() : List<MovieModel> {
    return this.results.map { movieDto ->
        MovieModel(
            id = movieDto.id,
            title = movieDto.title,
            image = movieDto.poster_path,
            description = movieDto.overview,
            popularity = movieDto.popularity,
            release_date = movieDto.release_date,
            genre_ids = movieDto.genre_ids
        )
    }
}