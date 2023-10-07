package com.dogactnrvrdi.movietime.view.home_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentHomeBinding
import com.dogactnrvrdi.movietime.view.home_screen.adapter.TopRatedMoviesAdapter
import com.dogactnrvrdi.movietime.view.home_screen.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    // Binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val viewModel: HomeViewModel by viewModels()

    // Adapters
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding
        _binding = FragmentHomeBinding.bind(view)

        // Adapter Setups
        setupTopRatedMoviesAdapter()

        viewModel.topRated.observe(viewLifecycleOwner) { movies ->
            topRatedMoviesAdapter.recyclerListDiffer.submitList(movies.results)
        }
    }

    private fun setupTopRatedMoviesAdapter() {
        topRatedMoviesAdapter = TopRatedMoviesAdapter()
        binding.rvTopRatedMovies.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = topRatedMoviesAdapter
        }

        topRatedMoviesAdapter.setOnItemClickListener { movie ->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
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