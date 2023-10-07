package com.dogactnrvrdi.movietime.view.movie_details_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.databinding.FragmentMovieDetailsBinding
import com.dogactnrvrdi.movietime.util.Constants.IMAGE_BASE_URL
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

    // Glide
    @Inject
    lateinit var glide: RequestManager

    // Movie Args
    private lateinit var movieId: String
    private lateinit var movieName: String
    private lateinit var releaseDate: String
    private lateinit var overview: String
    private lateinit var originalLanguage: String
    private lateinit var posterPath: String

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
            binding.apply {
                tvMovieName.text = movie.title
                glide.load(IMAGE_BASE_URL + posterPath).into(ivMoviePoster)
                tvMovieOriginalLanguage.text = originalLanguage
                tvMovieReleaseDate.text = releaseDate
                tvMovieOverview.text = overview
            }
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