package com.example.movieappsolera.ui.movies

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
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
        //Observamos la lista de peliculas
        val listObserver = Observer<List<MovieModel>>{
            movies = it
            moviesAdapter = MoviesAdapter(movies, this)
            binding.reciclerMovies.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()

            if (movies.isEmpty()){
                binding.message.visibility = View.VISIBLE
            }
        }
        viewModel.moviesListModel.observe(viewLifecycleOwner, listObserver)

        //Observamos mensaje de error
        val errorObserver = Observer<String>{
            binding.message.text = it
            if (it == ""){
                binding.message.visibility = View.GONE
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner, errorObserver)

        //Funcion para buscar peliculas con el SearchView
        setupSearchView()

    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getMovieListByNameFromApi(api_key, query!!)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                        viewModel.getMovieListByNameFromApi(api_key, newText)
                }
                return false
            }
        })
    }
    override fun onRecipeClick(movieModel: MovieModel, position : Int){
        findNavController().navigate(R.id.action_moviesFragment2_to_movieDetailFragment2, Bundle().apply {
            putInt("id", movieModel.id)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.moviesListModel.value = emptyList()
        viewModel.getMovieListPopularFromApi(api_key)
    }

}
