package com.example.movieappsolera.ui.Movies

import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappsolera.R
import com.example.movieappsolera.databinding.FragmentMoviesBinding
import com.example.movieappsolera.domain.model.MovieModel
import com.example.movieappsolera.utils.GlobalConstants.api_key
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesAdapter.OnRecipeClickListener {

    private lateinit var binding : FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    //Inicializamos variables
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var movies: List<MovieModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieListPopularFromApi(api_key)

        binding.reciclerMovies.layoutManager = LinearLayoutManager(context)
        //Observamos la lista de recetas
        val listObserver = Observer<List<MovieModel>>{
            movies = it
            moviesAdapter = MoviesAdapter(movies, this)
            binding.reciclerMovies.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
        viewModel.moviesListModel.observe(viewLifecycleOwner, listObserver)


    }


    override fun onRecipeClick(movieListModel: MovieModel, position : Int){

    }

}