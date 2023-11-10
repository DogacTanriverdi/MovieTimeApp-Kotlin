package com.dogactnrvrdi.movietime.presentation.tv_series_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailsViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    private val _tvSeries = MutableLiveData<TvSeries>()
    val tvSeries: LiveData<TvSeries> get() = _tvSeries

    private val language = Locale.getDefault().language

    fun getTvSeriesDetails(id: String) = viewModelScope.launch {
        repo.getTvSeriesDetails(id = id, language = language).let { tvSeries ->
            _tvSeries.value = tvSeries
        }
    }

    fun insertTvSeries() = viewModelScope.launch {
        tvSeries.value?.let { tvSeries ->
            repo.insertTvSeries(tvSeries)
        }
    }
}