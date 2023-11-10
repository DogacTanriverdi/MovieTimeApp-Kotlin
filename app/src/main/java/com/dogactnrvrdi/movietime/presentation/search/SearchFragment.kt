package com.dogactnrvrdi.movietime.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private val searchMovieAdapter by lazy { SearchMovieAdapter() }
    private val searchTvSeriesAdapter by lazy { SearchTvSeriesAdapter() }

    private var isMovie = false
    private var isTvSeries = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        with(binding) {

            etSearch.addTextChangedListener { searchQuery ->
                val query = searchQuery.toString()
                if (query.isNotEmpty()) {

                    setupSearchMovieAdapter()
                    viewModel.searchMovies(query)

                    chipTvSeries.setOnCheckedChangeListener { buttonView, isChecked ->
                        when {

                            isChecked -> {
                                setupSearchTvSeriesAdapter()
                                viewModel.searchTvSeries(query)
                                if (isTvSeries) {
                                    chipMovies.isChecked = false
                                }
                            }

                            else -> {
                                setupSearchMovieAdapter()
                                viewModel.searchMovies(query)
                                chipMovies.isChecked = true
                            }
                        }
                    }

                    chipMovies.setOnCheckedChangeListener { buttonView, isChecked ->
                        when {

                            isChecked -> {
                                setupSearchMovieAdapter()
                                viewModel.searchMovies(query)
                                if (isMovie) {
                                    chipTvSeries.isChecked = false
                                }
                            }

                            else -> {
                                setupSearchTvSeriesAdapter()
                                viewModel.searchTvSeries(query)
                                chipTvSeries.isChecked = true
                            }
                        }
                    }

                } else {
                    hideShimmerEffect()
                }
            }

            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        with(viewModel) {

            searchMovie.observe(viewLifecycleOwner) { searchMovies ->
                searchMovieAdapter.recyclerListDiffer.submitList(searchMovies.results)
                hideShimmerEffect()
            }

            searchTvSeries.observe(viewLifecycleOwner) { searchTvSeries ->
                searchTvSeriesAdapter.recyclerListDiffer.submitList(searchTvSeries.results)
                hideShimmerEffect()
            }
        }
    }

    private fun setupSearchMovieAdapter() {
        binding.rvSearchMovie.adapter = searchMovieAdapter

        isMovie = true
        isTvSeries = false

        searchMovieAdapter.setOnItemClickListener { movie ->
            val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(
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

    private fun setupSearchTvSeriesAdapter() {
        binding.rvSearchMovie.adapter = searchTvSeriesAdapter

        isMovie = false
        isTvSeries = true

        searchTvSeriesAdapter.setOnItemClickListener { tvSeries ->
            val action = SearchFragmentDirections.actionSearchFragmentToTvSeriesDetailsFragment(
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
            rvSearchMovie.visibility = View.GONE
        }
    }

    private fun hideShimmerEffect() {
        with(binding) {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            rvSearchMovie.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}