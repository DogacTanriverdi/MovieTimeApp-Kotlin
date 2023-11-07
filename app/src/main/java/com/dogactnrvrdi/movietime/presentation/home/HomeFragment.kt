package com.dogactnrvrdi.movietime.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val topRatedMoviesAdapter by lazy { TopRatedMoviesAdapter() }
    private val popularMoviesAdapter by lazy { PopularMoviesAdapter() }
    private val upcomingMoviesAdapter by lazy { UpcomingMoviesAdapter() }
    private val popularTvSeriesAdapter by lazy { PopularTvSeriesAdapter() }

    private var isMovie = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupTopRatedMoviesAdapter()

        setupPopularMoviesAdapter()

        setupUpcomingMoviesAdapter()

        setupPopularTvSeriesAdapter()

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        with(viewModel) {

            topRated.observe(viewLifecycleOwner) { topRatedMovies ->
                topRatedMoviesAdapter.recyclerListDiffer.submitList(topRatedMovies.results)
                isMovie = true
            }

            popular.observe(viewLifecycleOwner) { popularMovies ->
                popularMoviesAdapter.recyclerListDiffer.submitList(popularMovies.results)
                isMovie = true
            }

            upcoming.observe(viewLifecycleOwner) { upcomingMovies ->
                upcomingMoviesAdapter.recyclerListDiffer.submitList(upcomingMovies.results)
                isMovie = true
                hideShimmerEffect()
            }

            popularTvSeries.observe(viewLifecycleOwner) { popularTvSeries ->
                popularTvSeriesAdapter.recyclerListDiffer.submitList(popularTvSeries.results)
                isMovie = false
            }
        }
    }

    private fun setupTopRatedMoviesAdapter() {
        binding.rvTopRatedMovies.adapter = topRatedMoviesAdapter

        topRatedMoviesAdapter.setOnItemClickListener { movie ->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
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

    private fun setupPopularMoviesAdapter() {
        binding.rvPopularMovies.adapter = popularMoviesAdapter

        showShimmerEffect()

        popularMoviesAdapter.setOnItemClickListener { movie ->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
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

    private fun setupUpcomingMoviesAdapter() {
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter

        upcomingMoviesAdapter.setOnItemClickListener { movie ->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
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

    private fun setupPopularTvSeriesAdapter() {
        binding.rvPopularTvSeries.adapter = popularTvSeriesAdapter

        popularTvSeriesAdapter.setOnItemClickListener { tvSeries ->
            val action = HomeFragmentDirections.actionHomeFragmentToTvSeriesDetailsFragment(
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

    private fun showShimmerEffect() {
        with(binding) {
            shimmerFrameLayout.startShimmer()
            shimmerFrameLayout.visibility = View.VISIBLE
            nestedScrollView.visibility = View.GONE
        }
    }

    private fun hideShimmerEffect() {
        with(binding) {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            nestedScrollView.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}