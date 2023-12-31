package com.example.movieappsolera.data.remote.services

import com.example.movieappsolera.data.remote.models.movies.MovieListResponse
import com.example.movieappsolera.data.remote.models.moviesDetail.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceMovie {


    @GET("3/movie/popular")
    suspend fun getMoviesPopular(@Query("api_key") apiKey: String ): MovieListResponse


    @GET("3/movie/{movie_id}")
    suspend fun getMoviesById(@Path("movie_id") movieId : String ,@Query("api_key") apiKey: String ): MovieDetailDto

    @GET("3/search/movie")
    suspend fun getMoviesByName(@Query("api_key") apiKey: String, @Query("query") name: String): MovieListResponse
}