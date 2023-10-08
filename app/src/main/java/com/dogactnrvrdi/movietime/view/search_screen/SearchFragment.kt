package com.dogactnrvrdi.movietime.view.search_screen

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentSearchBinding
import com.dogactnrvrdi.movietime.view.search_screen.adapter.SearchAdapter
import com.dogactnrvrdi.movietime.view.search_screen.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    // Binding
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val viewModel: SearchViewModel by viewModels()

    // Adapter
    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding
        _binding = FragmentSearchBinding.bind(view)

        // Adapter Setup
        setupSearchAdapter()

        // Search Job
        var searchJob: Job? = null
        binding.etSearch.addTextChangedListener { searchQuery ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500)
                searchMovies()
            }
        }

        // Observer
        viewModel.searchMovie.observe(viewLifecycleOwner) { movie ->
            searchAdapter.recyclerListDiffer.submitList(movie)
        }

        // Back Button
        binding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    // Search Movies
    private fun searchMovies() {
        val searchQuery = binding.etSearch.text.toString()
        if (searchQuery.isNotEmpty()) viewModel.searchMovies(searchQuery)
    }

    // Search Adapter Setup
    private fun setupSearchAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearchMovie.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = searchAdapter
            setHasFixedSize(false)
        }

        searchAdapter.setOnItemClickListener { movie ->
            val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(
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