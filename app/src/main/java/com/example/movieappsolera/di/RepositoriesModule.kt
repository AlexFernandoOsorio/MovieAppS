package com.example.movieappsolera.di

import com.example.movieappsolera.data.repositories.MovieRepositoryimpl
import com.example.movieappsolera.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {


    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryimpl): MovieRepository
}