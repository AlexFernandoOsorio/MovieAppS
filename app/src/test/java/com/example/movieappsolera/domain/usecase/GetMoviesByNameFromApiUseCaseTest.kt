package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.repositories.MovieRepository
import com.example.movieappsolera.domain.usecase.GetMoviesByNameFromApiUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesByNameFromApiUseCaseTest{

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getMoviesByNameFromApiUseCase: GetMoviesByNameFromApiUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun whenGetMoviesByNameFromApiUseCaseIsCalledThenReturnListOfMovies()= runBlocking {

        // Given
        getMoviesByNameFromApiUseCase = GetMoviesByNameFromApiUseCase(movieRepository)

        // When
        val result = getMoviesByNameFromApiUseCase.invoke("1234", "Batman")

        // Then
        assert(result != null)
    }
}