package com.dogactnrvrdi.movietime.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.common.Constants.BASE_IMAGE_URL
import com.dogactnrvrdi.movietime.databinding.FragmentMovieDetailsBinding
import com.dogactnrvrdi.movietime.ui.detail.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)

        showShimmerEffect()

        with(viewModel) {
            with(binding) {

                getMovieDetails(args.movieId)

                movie.observe(viewLifecycleOwner) { movie ->
                    tvMovieName.text = movie.title
                    tvMovieName.isSelected = true
                    glide.load(BASE_IMAGE_URL + movie.posterPath).into(ivMoviePoster)
                    tvMovieOriginalLanguage.text =
                        "${getString(R.string.language)}: ${movie.originalLanguage}"
                    tvMovieReleaseDate.text =
                        "${getString(R.string.release_date)}: ${movie.releaseDate}"
                    tvMovieOverview.text = "${getString(R.string.overview)}:\n${movie.overview}"
                    hideShimmerEffect()
                }

                binding.fabAddFavorite.setOnClickListener {
                    insertMovie()
                    Toast.makeText(
                        context,
                        getString(R.string.movie_saved_successfully),
                        Toast.LENGTH_LONG
                    ).show()
                }

                ibBack.setOnClickListener { findNavController().navigateUp() }
            }
        }
    }

    private fun showShimmerEffect() {
        with(binding) {
            shimmerFrameLayout.startShimmer()
            shimmerFrameLayout.visibility = View.VISIBLE
            constraintLayout.visibility = View.GONE
        }
    }

    private fun hideShimmerEffect() {
        with(binding) {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            constraintLayout.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}