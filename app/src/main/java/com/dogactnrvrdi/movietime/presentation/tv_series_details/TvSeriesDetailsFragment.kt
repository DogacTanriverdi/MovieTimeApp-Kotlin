package com.dogactnrvrdi.movietime.presentation.tv_series_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.common.Constants
import com.dogactnrvrdi.movietime.databinding.FragmentTvSeriesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvSeriesDetailsFragment : Fragment(R.layout.fragment_tv_series_details) {

    private var _binding: FragmentTvSeriesDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvSeriesDetailsViewModel by viewModels()

    @Inject
    lateinit var glide: RequestManager

    private val args: TvSeriesDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTvSeriesDetailsBinding.bind(view)

        showShimmerEffect()

        with(binding) {
            with(viewModel) {

                getTvSeriesDetails(args.tvSeriesId)
            }

            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        observeData()
    }

    private fun observeData() {
        with(binding) {
            with(viewModel) {

                tvSeries.observe(viewLifecycleOwner) { tvSeries ->
                    tvTvSeriesName.text = tvSeries.name
                    tvTvSeriesName.isSelected = true
                    glide.load(Constants.BASE_IMAGE_URL + tvSeries.posterPath)
                        .into(ivTvSeriesPoster)
                    tvTvSeriesOriginalLanguage.text =
                        "${getString(R.string.original_language)}: ${tvSeries.originalLanguage}"
                    tvTvSeriesReleaseDate.text =
                        "${getString(R.string.release_date)}: ${tvSeries.firstAirDate}"
                    tvTvSeriesOverview.text =
                        "${getString(R.string.overview)}:\n${tvSeries.overview}"
                    tvTvSeriesOriginalTitle.text =
                        "${getString(R.string.original_title)}: ${tvSeries.originalName}"

                    hideShimmerEffect()
                }
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