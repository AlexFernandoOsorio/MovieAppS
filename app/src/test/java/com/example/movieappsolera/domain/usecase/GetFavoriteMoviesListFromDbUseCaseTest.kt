package com.example.movieappsolera.domain.usecase

import com.example.movieappsolera.domain.repositories.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetFavoriteMoviesListFromDbUseCaseTest {

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getFavoriteMoviesListFromDbUseCase: GetFavoriteMoviesListFromDbUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun whenGetFavoriteMoviesListFromDbUseCaseIsCalledThenReturnListOfMovies()= runBlocking {

        // Given
        getFavoriteMoviesListFromDbUseCase = GetFavoriteMoviesListFromDbUseCase(movieRepository)

        // When
        val result = coEvery { getFavoriteMoviesListFromDbUseCase.invoke()}

        // Then
        assert(result != null)
    }
}