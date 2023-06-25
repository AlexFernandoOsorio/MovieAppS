package com.example.movieappsolera.ui.moviesdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieappsolera.domain.model.MovieDetailModel
import com.example.movieappsolera.domain.usecase.DeleteFavoriteMovieFromDbUseCase
import com.example.movieappsolera.domain.usecase.GetMovieByIdFromApiUseCase
import com.example.movieappsolera.domain.usecase.InsertFavoriteMovieToDbUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class MovieDetailViewModelTest {

    @RelaxedMockK
    private lateinit var getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase
    @RelaxedMockK
    private lateinit var insertFavoriteMovieToDb : InsertFavoriteMovieToDbUseCase
    @RelaxedMockK
    private lateinit var deleteFavoriteMovieFromDb: DeleteFavoriteMovieFromDbUseCase

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        movieDetailViewModel = MovieDetailViewModel()
        Dispatchers.Unconfined
    }

    @Test
    fun getGetMovieDetailFromApiUseCase() = runBlocking {

        // Given
        movieDetailViewModel = MovieDetailViewModel()

        // When
        val result = getMovieByIdFromApiUseCase("1234", "1234")

        // Then
        assert(result != null)
    }

    @Test
    fun getInsertFavoriteMovieToDbUseCase() = runBlocking {
        // Given
        movieDetailViewModel = MovieDetailViewModel()
        val movie = MovieDetailModel(
            1234,
            "Batman",
            "2020",
            "www.google.com",
            10.0,
            "www.google.com",
            List(2, { "www.google.com" }),
        )
        // When
        val result =  coEvery { insertFavoriteMovieToDb(movie) }

        // Then
        assert(result != null)
    }
}