package com.dogactnrvrdi.movietime.view.home_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentHomeBinding
import com.dogactnrvrdi.movietime.view.home_screen.adapter.PopularMoviesAdapter
import com.dogactnrvrdi.movietime.view.home_screen.adapter.TopRatedMoviesAdapter
import com.dogactnrvrdi.movietime.view.home_screen.adapter.UpcomingMoviesAdapter
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
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding
        _binding = FragmentHomeBinding.bind(view)

        // Adapter Setups
        setupTopRatedMoviesAdapter()
        setupPopularMoviesAdapter()
        setupUpcomingMoviesAdapter()

        // Observers
        viewModel.topRated.observe(viewLifecycleOwner) { topRatedMovies ->
            topRatedMoviesAdapter.recyclerListDiffer.submitList(topRatedMovies.results)
        }

        viewModel.popular.observe(viewLifecycleOwner) { popularMovies ->
            popularMoviesAdapter.recyclerListDiffer.submitList(popularMovies.results)
        }

        viewModel.upcoming.observe(viewLifecycleOwner) { upcomingMovies ->
            upcomingMoviesAdapter.recyclerListDiffer.submitList(upcomingMovies.results)
        }
    }

    // Top Rated Movies Adapter Setup
    private fun setupTopRatedMoviesAdapter() {
        topRatedMoviesAdapter = TopRatedMoviesAdapter()
        binding.rvTopRatedMovies.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = topRatedMoviesAdapter
            setHasFixedSize(true)
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

    // Popular Movies Adapter Setup
    private fun setupPopularMoviesAdapter() {
        popularMoviesAdapter = PopularMoviesAdapter()
        binding.rvPopularMovies.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = popularMoviesAdapter
            setHasFixedSize(true)
        }

        popularMoviesAdapter.setOnItemClickListener { movie ->
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

    // Upcoming Movies Adapter Setup
    private fun setupUpcomingMoviesAdapter() {
        upcomingMoviesAdapter = UpcomingMoviesAdapter()
        binding.rvUpcomingMovies.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = upcomingMoviesAdapter
            setHasFixedSize(true)
        }

        upcomingMoviesAdapter.setOnItemClickListener { movie ->
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