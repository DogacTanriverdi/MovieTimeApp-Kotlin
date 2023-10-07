package com.dogactnrvrdi.movietime.view.HomeScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentHomeBinding
import com.dogactnrvrdi.movietime.view.HomeScreen.adapter.TopRatedMoviesAdapter
import com.dogactnrvrdi.movietime.view.HomeScreen.viewmodel.HomeViewModel
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}