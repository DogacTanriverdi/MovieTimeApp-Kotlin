package com.dogactnrvrdi.movietime.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentHomeBinding
import com.dogactnrvrdi.movietime.presentation.home.movie_adapter.NowPlayingMoviesAdapter
import com.dogactnrvrdi.movietime.presentation.home.movie_adapter.PopularMoviesAdapter
import com.dogactnrvrdi.movietime.presentation.home.movie_adapter.TrendingMoviesAdapter
import com.dogactnrvrdi.movietime.presentation.home.tv_series_adapter.AiringTodayTvSeriesAdapter
import com.dogactnrvrdi.movietime.presentation.home.tv_series_adapter.OnTheAirTvSeriesAdapter
import com.dogactnrvrdi.movietime.presentation.home.tv_series_adapter.PopularTvSeriesAdapter
import com.dogactnrvrdi.movietime.presentation.home.tv_series_adapter.TrendingTvSeriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val trendingMoviesAdapter by lazy { TrendingMoviesAdapter() }
    private val nowPlayingMoviesAdapter by lazy { NowPlayingMoviesAdapter() }
    private val popularMoviesAdapter by lazy { PopularMoviesAdapter() }
    private val upcomingMoviesAdapter by lazy { UpcomingMoviesAdapter() }

    private val trendingTvSeriesAdapter by lazy { TrendingTvSeriesAdapter() }
    private val airingTodayTvSeriesAdapter by lazy { AiringTodayTvSeriesAdapter() }
    private val onTheAirTvSeriesAdapter by lazy { OnTheAirTvSeriesAdapter() }
    private val popularTvSeriesAdapter by lazy { PopularTvSeriesAdapter() }

    private var isMovie = false
    private var isTvSeries = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        showShimmerEffect()

        setupTrendingMoviesAdapter()
        setupNowPlayingMoviesAdapter()
        setupPopularAdapter()
        setupUpcomingMoviesAdapter()

        with(binding) {

            chipTvSeries.setOnCheckedChangeListener { buttonView, isChecked ->
                when {

                    isChecked -> {

                        tvTrendingMovies.text = getString(R.string.trending_tv_series)
                        tvNowPlayingMovies.text = getString(R.string.airing_today_tv_series)
                        tvPopularMovies.text = getString(R.string.on_the_air_tv_series)
                        tvUpcomingMovies.text = getString(R.string.popular_tv_series)

                        setupTrendingTvSeriesAdapter()
                        setupAiringTodayTvSeriesAdapter()
                        setupOnTheAirTvSeriesAdapter()
                        setupPopularTvSeriesAdapter()
                        if (isTvSeries) {
                            chipMovies.isChecked = false
                        }
                    }

                    else -> {

                        tvTrendingMovies.text = getString(R.string.trending_movies)
                        tvNowPlayingMovies.text = getString(R.string.now_playing)
                        tvPopularMovies.text = getString(R.string.popular_movies)
                        tvUpcomingMovies.text = getString(R.string.upcoming_movies)

                        setupTrendingMoviesAdapter()
                        setupNowPlayingMoviesAdapter()
                        setupPopularAdapter()
                        setupUpcomingMoviesAdapter()
                        chipMovies.isChecked = true
                    }
                }
            }

            chipMovies.setOnCheckedChangeListener { buttonView, isChecked ->
                when {

                    isChecked -> {

                        tvTrendingMovies.text = getString(R.string.trending_movies)
                        tvNowPlayingMovies.text = getString(R.string.now_playing)
                        tvPopularMovies.text = getString(R.string.popular_movies)
                        tvUpcomingMovies.text = getString(R.string.upcoming_movies)

                        setupTrendingMoviesAdapter()
                        setupNowPlayingMoviesAdapter()
                        setupPopularAdapter()
                        setupUpcomingMoviesAdapter()
                        if (isMovie) {
                            chipTvSeries.isChecked = false
                        }
                    }

                    else -> {

                        tvTrendingMovies.text = getString(R.string.trending_tv_series)
                        tvNowPlayingMovies.text = getString(R.string.airing_today_tv_series)
                        tvPopularMovies.text = getString(R.string.on_the_air_tv_series)
                        tvUpcomingMovies.text = getString(R.string.popular_tv_series)

                        setupTrendingTvSeriesAdapter()
                        setupAiringTodayTvSeriesAdapter()
                        setupOnTheAirTvSeriesAdapter()
                        setupPopularTvSeriesAdapter()
                        chipTvSeries.isChecked = true
                    }
                }
            }
        }

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        with(viewModel) {

            trendingMoviesDay.observe(viewLifecycleOwner) { trendingMovies ->
                trendingMoviesAdapter.recyclerListDiffer.submitList(trendingMovies.results)
            }

            nowPlayingMovies.observe(viewLifecycleOwner) { nowPlayingMovies ->
                nowPlayingMoviesAdapter.recyclerListDiffer.submitList(nowPlayingMovies.results)
            }

            popularMovies.observe(viewLifecycleOwner) { popularMovies ->
                popularMoviesAdapter.recyclerListDiffer.submitList(popularMovies.results)
                hideShimmerEffect()
            }

            upcomingMovies.observe(viewLifecycleOwner) { upcomingMovies ->
                upcomingMoviesAdapter.recyclerListDiffer.submitList(upcomingMovies.results)
            }

            trendingTvSeriesDay.observe(viewLifecycleOwner) { trendingTvSeries ->
                trendingTvSeriesAdapter.recyclerListDiffer.submitList(trendingTvSeries.results)
            }

            airingTodayTvSeries.observe(viewLifecycleOwner) { airingTodayTvSeries ->
                airingTodayTvSeriesAdapter.recyclerListDiffer.submitList(airingTodayTvSeries.results)
            }

            onTheAirTvSeries.observe(viewLifecycleOwner) { onTheAirTvSeries ->
                onTheAirTvSeriesAdapter.recyclerListDiffer.submitList(onTheAirTvSeries.results)
            }

            popularTvSeries.observe(viewLifecycleOwner) { popularTvSeries ->
                popularTvSeriesAdapter.recyclerListDiffer.submitList(popularTvSeries.results)
            }
        }
    }

    private fun setupTrendingMoviesAdapter() {
        binding.rvTrendingMovies.adapter = trendingMoviesAdapter

        isMovie = true
        isTvSeries = false

        trendingMoviesAdapter.setOnItemClickListener { movie ->
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

    private fun setupNowPlayingMoviesAdapter() {
        binding.rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter

        isMovie = true
        isTvSeries = false

        nowPlayingMoviesAdapter.setOnItemClickListener { movie ->
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

    private fun setupPopularAdapter() {
        binding.rvPopularMovies.adapter = popularMoviesAdapter

        isMovie = true
        isTvSeries = false

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

        isMovie = true
        isTvSeries = false

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

    private fun setupTrendingTvSeriesAdapter() {
        binding.rvTrendingMovies.adapter = trendingTvSeriesAdapter

        isMovie = false
        isTvSeries = true

        trendingTvSeriesAdapter.setOnItemClickListener { tvSeries ->
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

    private fun setupAiringTodayTvSeriesAdapter() {
        binding.rvNowPlayingMovies.adapter = airingTodayTvSeriesAdapter

        isMovie = false
        isTvSeries = true

        airingTodayTvSeriesAdapter.setOnItemClickListener { tvSeries ->
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

    private fun setupOnTheAirTvSeriesAdapter() {
        binding.rvPopularMovies.adapter = onTheAirTvSeriesAdapter

        isMovie = false
        isTvSeries = true

        onTheAirTvSeriesAdapter.setOnItemClickListener { tvSeries ->
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

    private fun setupPopularTvSeriesAdapter() {
        binding.rvUpcomingMovies.adapter = popularTvSeriesAdapter

        isMovie = false
        isTvSeries = true

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