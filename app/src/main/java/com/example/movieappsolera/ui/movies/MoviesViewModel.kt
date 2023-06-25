package com.example.movieappsolera.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.usecase.GetMoviesByNameFromApiUseCase
import com.example.movieappsolera.domain.usecase.GetMovieListFromApiUseCase
import com.example.movieappsolera.utils.GlobalConstants.noSearchMovies
import com.example.movieappsolera.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(): ViewModel(){

    @Inject
    lateinit var GetMovieListFromApiUseCase: GetMovieListFromApiUseCase

    @Inject
    lateinit var GetMoviesByNameFromApiUseCase: GetMoviesByNameFromApiUseCase

    private var _moviesListModel = MutableLiveData<List<MovieModel>>()
    var moviesListModel: MutableLiveData<List<MovieModel>> = _moviesListModel

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: MutableLiveData<String> = _errorMessage

    private var _isError = MutableLiveData<Boolean>()
    var isError: MutableLiveData<Boolean> = _isError

    fun getMovieListPopularFromApi(apiKey : String) {
        viewModelScope.launch() {
            _moviesListModel.postValue(emptyList())
            val movieList = GetMovieListFromApiUseCase.invoke(apiKey)
            movieList.collect {
                when(it){
                    is UIEvent.Loading -> {
                        _moviesListModel.postValue(emptyList())
                        _errorMessage.postValue("")
                        isError.postValue(false)
                    }
                    is UIEvent.Success -> {
                        _moviesListModel.postValue(it.data!!.toList())
                        _errorMessage.postValue("")
                        isError.postValue(false)
                    }
                    is UIEvent.Error -> {
                        _moviesListModel.postValue(emptyList())
                        _errorMessage.postValue(it.message!!)
                        isError.postValue(true)
                    }
                }
            }

        }
    }

    fun getMovieListByNameFromApi(apiKey : String,name : String) {
        viewModelScope.launch() {
            _moviesListModel.postValue(emptyList())
            val movieList = GetMoviesByNameFromApiUseCase(apiKey,name)
            movieList.collect {
                when(it){
                    is UIEvent.Loading -> {
                        _moviesListModel.postValue(emptyList())
                        _errorMessage.postValue("")
                    }
                    is UIEvent.Success -> {
                        _moviesListModel.postValue(it.data?.toList() ?: emptyList())
                        _errorMessage.postValue("")
                    }
                    is UIEvent.Error -> {
                        _moviesListModel.postValue(emptyList())
                        it.message = "Error"
                        _errorMessage.postValue(noSearchMovies+name)
                    }
                }
            }

        }
    }
}