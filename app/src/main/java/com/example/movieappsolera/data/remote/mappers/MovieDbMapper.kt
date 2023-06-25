package com.example.movieappsolera.data.remote.mappers

import com.example.movieappsolera.data.local.entities.MovieEntity
import com.example.movieappsolera.domain.model.MovieDetailModel

fun MovieEntity.toDomainMovieList() : MovieDetailModel {
    return MovieDetailModel(
        id = this.id,
        title = this.name,
        image = this.image,
        description = this.description,
        popularity = this.popularity.toDouble(),
        release_date = this.release_date,
        genre_ids = List(0) { this.genre},
    )
}
