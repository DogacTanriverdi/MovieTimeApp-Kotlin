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

    private val favoriteMoviesAdapter by lazy { FavoriteMoviesAdapter() }
    private val favoriteTvSeriesAdapter by lazy { FavoriteTvSeriesAdapter() }

    private var isMovie = false
    private var isTvSeries = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        setupFavoriteMoviesAdapter()

        with(binding) {

            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }

            chipTvSeries.setOnCheckedChangeListener { buttonView, isChecked ->
                when {

                    isChecked -> {
                        setupFavoriteTvSeriesAdapter()
                        if (isTvSeries) {
                            chipMovies.isChecked = false
                        }
                    }

                    else -> {
                        setupFavoriteMoviesAdapter()
                        chipMovies.isChecked = true
                    }
                }
            }

            chipMovies.setOnCheckedChangeListener { buttonView, isChecked ->
                when {

                    isChecked -> {
                        setupFavoriteMoviesAdapter()
                        if (isMovie) {
                            chipTvSeries.isChecked = false
                        }
                    }

                    else -> {
                        setupFavoriteTvSeriesAdapter()
                        chipTvSeries.isChecked = true
                    }
                }
            }
        }

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        with(viewModel) {

            movies.observe(viewLifecycleOwner) { movie ->
                favoriteMoviesAdapter.recyclerListDiffer.submitList(movie)
            }

            tvSeries.observe(viewLifecycleOwner) { tvSeries ->
                favoriteTvSeriesAdapter.recyclerListDiffer.submitList(tvSeries)
            }
        }
    }

    private fun setupFavoriteMoviesAdapter() {
        binding.rvFavorites.adapter = favoriteMoviesAdapter

        isMovie = true
        isTvSeries = false

        favoriteMoviesAdapter.setOnItemClickListener { movie ->
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

    private fun setupFavoriteTvSeriesAdapter() {
        binding.rvFavorites.adapter = favoriteTvSeriesAdapter

        isTvSeries = true
        isMovie = false

        favoriteTvSeriesAdapter.setOnItemClickListener { tvSeries ->
            val action =
                FavoritesFragmentDirections.actionFavoritesFragmentToTvSeriesDetailsFragment(
                    tvSeriesId = tvSeries.id.toString(),
                    tvSeriesName = tvSeries.name,
                    tvSeriesReleaseDate = tvSeries.firstAirDate,
                    tvSeriesOverview = tvSeries.overview,
                    tvSeriesOriginalLanguage = tvSeries.originalLanguage,
                    tvSeriesPosterPath = tvSeries.posterPath,
                    tvSeriesOriginalTitle = tvSeries.originalName
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}