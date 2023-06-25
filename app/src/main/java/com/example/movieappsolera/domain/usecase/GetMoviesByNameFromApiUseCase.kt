package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.repositories.MovieRepository
import com.example.movieappsolera.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesByNameFromApiUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(apiKey : String, name : String) = flow<UIEvent<List<MovieModel>>> {
        emit(UIEvent.Loading())
        val movieList = movieRepository.getMovieByNameFromRemote(apiKey, name)
        emit(UIEvent.Success(movieList))
    }.catch {
        emit(UIEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)


}