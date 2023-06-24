package com.example.movieappsolera.ui.Movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.usecase.GetMovieListFromApiUseCase
import com.example.movieappsolera.utils.GlobalConstants.api_key
import com.example.movieappsolera.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(): ViewModel(){

    @Inject
    lateinit var GetMovieListFromApiUseCase: GetMovieListFromApiUseCase

    val apiKey = api_key
    private var _moviesListModel = MutableLiveData<List<MovieModel>>()
    var moviesListModel: LiveData<List<MovieModel>> = _moviesListModel


    private val _query : MutableStateFlow<String> = MutableStateFlow("")
    val query : StateFlow<String> = _query

    /*init {
        viewModelScope.launch {
            _query.debounce(1000).collectLatest {
                getMovieListPopularFromApi(apiKey)
            }
        }
    }*/



    fun getMovieListPopularFromApi(apiKey : String) {
        viewModelScope.launch() {
            _moviesListModel.postValue(emptyList())
            val movieList = GetMovieListFromApiUseCase.invoke(apiKey)
            movieList.collect {
                when(it){
                    is UIEvent.Loading -> {
                        _moviesListModel.postValue(emptyList())
                    }
                    is UIEvent.Success -> {
                        _moviesListModel.postValue(it.data?.toList() ?: emptyList())
                    }
                    is UIEvent.Error -> {
                        _moviesListModel.postValue(emptyList())
                        it.message = "Error"
                    }
                }
            }

        }
    }
}