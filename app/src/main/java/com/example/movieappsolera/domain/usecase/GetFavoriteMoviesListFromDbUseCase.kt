package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.model.MovieDetailModel
import com.example.movieappsolera.domain.repositories.MovieRepository
import javax.inject.Inject

class GetFavoriteMoviesListFromDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke() : List<MovieDetailModel>{

        var lista : List<MovieDetailModel> = movieRepository.getMovieListFromLocal()

        return lista
    }
}