package com.example.movieappsolera.data.remote.services

import com.example.movieappsolera.data.remote.models.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceMovie {


    @GET("3/movie/popular")
    suspend fun getMoviesPopular(@Query("api_key") apiKey: String ): MovieListResponse

}