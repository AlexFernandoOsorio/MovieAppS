package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.repositories.MovieRepository
import com.example.movieappsolera.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieListFromApiUseCase @Inject constructor(private val movieRepository: MovieRepository) {


    operator fun invoke(apiKey : String) = flow<UIEvent<List<MovieModel>>> {
        emit(UIEvent.Loading())
        val movieList = movieRepository.getMovieListPopularFromRemote(apiKey)
        emit(UIEvent.Success(movieList))
    }.catch {
        emit(UIEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}