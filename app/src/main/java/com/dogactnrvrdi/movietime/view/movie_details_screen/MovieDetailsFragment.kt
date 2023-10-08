package com.dogactnrvrdi.movietime.view.movie_details_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentMovieDetailsBinding
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.util.Constants.BASE_IMAGE_URL
import com.dogactnrvrdi.movietime.view.movie_details_screen.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    // Binding
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val viewModel: MovieDetailsViewModel by viewModels()

    // Movie
    private var movie: MovieDetails? = null

    // Glide
    @Inject
    lateinit var glide: RequestManager

    // Movie Args
    private lateinit var movieId: String
    private var movieName: String? = null
    private var releaseDate: String? = null
    private var overview: String? = null
    private var originalLanguage: String? = null
    private var posterPath: String? = null

    // Get Args
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding
        _binding = FragmentMovieDetailsBinding.bind(view)

        // Get Args
        getArgs()

        // ViewModel
        viewModel.getMovieDetails(movieId)
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            this.movie = movie
            binding.apply {
                tvMovieName.text = movie.title
                glide.load(BASE_IMAGE_URL + posterPath).into(ivMoviePoster)
                tvMovieOriginalLanguage.text = originalLanguage
                tvMovieReleaseDate.text = releaseDate
                tvMovieOverview.text = overview
            }
        }

        // On Add Favorites Button Clicked
        binding.fabAddFavorite.setOnClickListener {
            movie?.let { movie -> viewModel.insertMovie(requireContext(), movie) }
        }

        // On Back Button Clicked
        binding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getArgs() {
        movieId = args.movieId
        movieName = args.movieName.toString()
        releaseDate = args.movieReleaseDate.toString()
        overview = args.movieOverview.toString()
        originalLanguage = args.movieOriginalLanguage.toString()
        posterPath = args.moviePosterPath.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}