package com.dogactnrvrdi.movietime.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    private val favoritesAdapter by lazy { FavoritesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        binding.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }

        setupFavoritesAdapter()

        observeData()
    }

    private fun observeData() {
        viewModel.movies.observe(viewLifecycleOwner) { movie ->
            favoritesAdapter.recyclerListDiffer.submitList(movie)
        }
    }

    private fun setupFavoritesAdapter() {
        binding.rvFavorites.adapter = favoritesAdapter

        favoritesAdapter.setOnItemClickListener { movie ->
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailsFragment(
                movieId = movie.id.toString(),
                movieName = movie.title,
                movieReleaseDate = movie.releaseDate,
                movieOverview = movie.overview,
                movieOriginalLanguage = movie.originalLanguage,
                moviePosterPath = movie.posterPath,
                movieOriginalTitle = movie.originalTitle
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}