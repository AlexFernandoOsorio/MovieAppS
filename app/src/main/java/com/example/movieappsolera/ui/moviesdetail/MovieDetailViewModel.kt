package com.example.movieappsolera.ui.moviesdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappsolera.domain.model.MovieDetailModel
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.domain.usecase.DeleteFavoriteMovieFromDbUseCase
import com.example.movieappsolera.domain.usecase.GetFavoriteMovieByIdFromDbUseCase
import com.example.movieappsolera.domain.usecase.GetMovieByIdFromApiUseCase
import com.example.movieappsolera.domain.usecase.InsertFavoriteMovieToDbUseCase
import com.example.movieappsolera.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(): ViewModel(){

    @Inject
    lateinit var GetMovieDetailFromApiUseCase: GetMovieByIdFromApiUseCase
    @Inject
    lateinit var insertFavoriteMovieToDb : InsertFavoriteMovieToDbUseCase
    @Inject
    lateinit var getFavoriteMovieByIdFromDbUseCase: GetFavoriteMovieByIdFromDbUseCase
    @Inject
    lateinit var deleteFavoriteMovieFromDb: DeleteFavoriteMovieFromDbUseCase

    //Inicializamos los livedata
    private var _movieModel = MutableLiveData<MovieDetailModel?>()
    var movieModel: MutableLiveData<MovieDetailModel?> = _movieModel

    private var _isFavorite = MutableLiveData<Boolean>()
    var isFavorite: MutableLiveData<Boolean> = _isFavorite



    //Funcion para obtener la lista de peliculas por Id mediante llamada a la api
    fun getMovieByIdFromApi(movieId : String ,apiKey : String) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieModel.postValue(null)
            val movieList = GetMovieDetailFromApiUseCase(movieId,apiKey)
            movieList.collect {
                when(it){
                    is UIEvent.Loading -> {
                        _movieModel.postValue(null)
                    }
                    is UIEvent.Success -> {
                        _movieModel.postValue(it.data!!)
                    }
                    is UIEvent.Error -> {
                        _movieModel.postValue(null)
                        it.message = "Error"
                    }
                }
            }

        }
    }

    //Funcion para agregar una pelicula a favoritos
    fun insertFavoriteMovie() {
        viewModelScope.launch() {
            insertFavoriteMovieToDb(movieModel.value!!)
        }
    }

    //Funcion para eliminar una pelicula de favoritos
    fun deleteFavoriteMovie() {
        viewModelScope.launch() {
            deleteFavoriteMovieFromDb(movieModel.value!!)
        }
    }
}