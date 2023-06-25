package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.repositories.MovieRepository
import javax.inject.Inject

class GetFavoriteMovieByIdFromDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {

        suspend operator fun invoke(id : Int) = movieRepository.getMovieByIdFromLocal(id)?.let {
            it
        }
}