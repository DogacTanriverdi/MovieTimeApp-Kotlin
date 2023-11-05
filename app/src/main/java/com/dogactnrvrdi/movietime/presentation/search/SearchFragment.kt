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

    private val searchAdapter by lazy { SearchAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        with(binding) {

            etSearch.addTextChangedListener { searchQuery ->
                val query = searchQuery.toString()
                if (query.isNotEmpty()) {
                    viewModel.searchMovies(query)
                    showShimmerEffect()
                } else {
                    hideShimmerEffect()
                }
            }

            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        setupSearchAdapter()

        observeData()
    }

    private fun observeData() {
        viewModel.searchMovie.observe(viewLifecycleOwner) { searchMovies ->
            searchAdapter.recyclerListDiffer.submitList(searchMovies.results)
            hideShimmerEffect()
        }
    }

    private fun setupSearchAdapter() {
        binding.rvSearchMovie.adapter = searchAdapter

        searchAdapter.setOnItemClickListener { movie ->
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