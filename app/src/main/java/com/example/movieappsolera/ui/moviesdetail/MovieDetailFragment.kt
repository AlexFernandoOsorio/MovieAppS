package com.example.movieappsolera.ui.moviesdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieappsolera.BuildConfig
import com.example.movieappsolera.R
import com.example.movieappsolera.databinding.FragmentMovieDetailBinding
import com.example.movieappsolera.domain.model.MovieDetailModel
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.utils.GlobalConstants

import com.example.movieappsolera.utils.GlobalConstants.api_key
import com.example.movieappsolera.utils.GlobalConstants.poster_path
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    //Inicializamos binding y viewModel
    private lateinit var binding : FragmentMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    //Inicializamos variables
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Llamamos a la API para obtener los datos de la pelicula seleccionada por Id
        viewModel.getMovieByIdFromApi(requireArguments().getInt("id").toString(),api_key)

        //Observamos el modelo de datos de la pelicula
        val movieModelObserver = Observer<MovieDetailModel?>{
            //Funcion let para evitar nullPointerException
            it?.let {
                //Bloque de codigo para mostrar los datos de la pelicula
                binding.movieName.text = it.title
                binding.movieFecha.text = it.release_date
                binding.movieDescripcion.text = it.description
                var generos = ""
                for (item in it.genre_ids) {
                    generos = generos + " • " +item
                }
                binding.movieGenero.text = generos
                Glide.with(binding.movieImage.context)
                    .load(poster_path + it.image)
                    .centerCrop()
                    .into(binding.movieImage)
                binding.movieRatingBar.rating = it.popularity.toFloat()
            }
        }
        viewModel.movieModel.observe(viewLifecycleOwner,movieModelObserver)

        //Agregamos o no la pelicula a favoritos
        binding.btnFavorite.setOnClickListener(View.OnClickListener {
            viewModel.insertFavoriteMovie()
            isFavorite = !isFavorite
            viewModel.isFavorite.postValue(isFavorite)
            if (isFavorite)
                binding.btnFavorite.imageTintList = resources.getColorStateList(R.color.red_primary)
            else
                binding.btnFavorite.imageTintList = resources.getColorStateList(R.color.blue_primary)
        })

    }

}