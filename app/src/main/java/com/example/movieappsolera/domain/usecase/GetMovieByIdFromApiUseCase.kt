package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.model.MovieDetailModel
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.repositories.MovieRepository
import com.example.movieappsolera.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieByIdFromApiUseCase @Inject constructor(private val movieRepository: MovieRepository) {


    operator fun invoke(movieId : String , apiKey : String) = flow<UIEvent<MovieDetailModel>> {
        emit(UIEvent.Loading())
        val movieDetail = movieRepository.getMovieByIdFromRemote(movieId,apiKey)
        emit(UIEvent.Success(movieDetail))
    }.catch {
        emit(UIEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}