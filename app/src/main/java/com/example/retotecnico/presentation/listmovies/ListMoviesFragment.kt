package com.example.retotecnico.presentation.listmovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.retotecnico.R
import com.example.retotecnico.data.MoviesRepository
import com.example.retotecnico.databinding.FragmentListMoviesBinding
import com.example.retotecnico.domain.model.Movie
import com.example.retotecnico.domain.usecase.listmovies.GetMoviesUseCase

class ListMoviesFragment : Fragment(R.layout.fragment_list_movies) {
    private val viewModel: ListMoviesViewModel by viewModels {
        val repository = MoviesRepository(requireActivity().application)
        ListMoviesViewModelFactory(GetMoviesUseCase(repository))
    }
    private lateinit var binding: FragmentListMoviesBinding
    private val adapter = MoviesAdapter { onMovieClicked(it) }
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding = FragmentListMoviesBinding.bind(view).apply {
            recyclerMovies.adapter = adapter
        }
        configViewModel()
        viewModel.refresh()
    }

    private fun configViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onMovieClicked(movie: Movie) {
        val action =
            ListMoviesFragmentDirections.actionListMoviesFragmentToDetailMovieFragment(movie.id)
        navController.navigate(action)
    }

    private fun updateUI(state: ListMoviesViewModel.UiState) {
        adapter.submitList(state.movies)
    }

}