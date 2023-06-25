package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.model.MovieDetailModel
import com.example.movieappsolera.domain.repositories.MovieRepository
import javax.inject.Inject

class InsertFavoriteMovieToDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieDetailModel: MovieDetailModel){
        movieRepository.insertMovieToLocal(movieDetailModel)
    }
}