package com.dogactnrvrdi.movietime.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.search.SearchMovies
import com.dogactnrvrdi.movietime.data.model.tv_search.SearchTvSeries
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    // Search Movie
    private val _searchMovie = MutableLiveData<SearchMovies>()
    val searchMovie: LiveData<SearchMovies> get() = _searchMovie

    private val _searchTvSeries = MutableLiveData<SearchTvSeries>()
    val searchTvSeries: LiveData<SearchTvSeries> get() = _searchTvSeries

    private val language = Locale.getDefault().language

    fun searchMovies(searchQuery: String) = viewModelScope.launch {
        delay(500)
        repo.searchMovies(searchQuery, language).let { movie ->
            _searchMovie.value = movie
        }
    }

    fun searchTvSeries(searchQuery: String) = viewModelScope.launch {
        delay(500)
        repo.searchTvSeries(searchQuery, language).let { tvSeries ->
            _searchTvSeries.value = tvSeries
        }
    }
}