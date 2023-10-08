package com.dogactnrvrdi.movietime.view.favorites_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentFavoritesBinding
import com.dogactnrvrdi.movietime.view.favorites_screen.adapter.FavoritesAdapter
import com.dogactnrvrdi.movietime.view.favorites_screen.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    // Binding
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val viewModel: FavoritesViewModel by viewModels()

    // Adapter
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding
        _binding = FragmentFavoritesBinding.bind(view)

        // ViewModel & Observers
        viewModel.movies.observe(viewLifecycleOwner) { movie ->
            favoritesAdapter.recyclerListDiffer.submitList(movie)
        }

        // Adapter Setup
        setupFavoritesAdapter()
    }

    private fun setupFavoritesAdapter() {
        favoritesAdapter = FavoritesAdapter()
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = favoritesAdapter
        }

        favoritesAdapter.setOnItemClickListener { movie ->
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailsFragment(
                movieId = movie.id.toString(),
                movieName = movie.title,
                movieReleaseDate = movie.release_date,
                movieOverview = movie.overview,
                movieOriginalLanguage = movie.original_language,
                moviePosterPath = movie.poster_path
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}