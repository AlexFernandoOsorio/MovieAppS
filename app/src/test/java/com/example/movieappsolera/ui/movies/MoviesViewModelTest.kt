package com.example.movieappsolera.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieappsolera.domain.usecase.GetMovieListFromApiUseCase
import com.example.movieappsolera.domain.usecase.GetMoviesByNameFromApiUseCase
import com.example.movieappsolera.ui.moviesdetail.MovieDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest{

    @RelaxedMockK
    private lateinit var GetMovieListFromApiUseCase: GetMovieListFromApiUseCase
    @RelaxedMockK
    private lateinit var GetMoviesByNameFromApiUseCase: GetMoviesByNameFromApiUseCase

    lateinit var moviesViewModel: MoviesViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel()
        Dispatchers.Unconfined
    }


    @Test
    fun getGetMovieListFromApiUseCase() {
        // Given
        moviesViewModel = MoviesViewModel()

        // When
        val result = GetMovieListFromApiUseCase.invoke("1234")

        // Then
        assert(result != null)
    }

    @Test
    fun getGetMoviesByNameFromApiUseCase() {
        // Given
        moviesViewModel = MoviesViewModel()

        // When
        val result = GetMoviesByNameFromApiUseCase.invoke("1234", "Batman")

        // Then
        assert(result != null)
    }
}