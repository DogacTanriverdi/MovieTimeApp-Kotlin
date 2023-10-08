package com.dogactnrvrdi.movietime.view.search_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.repo.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: IMovieRepository
) : ViewModel() {

    // Search Movie
    private val _searchMovie = MutableLiveData<List<MovieDetails>>()
    val searchMovie: LiveData<List<MovieDetails>> get() = _searchMovie

    fun searchMovies(searchQuery: String) {
        viewModelScope.launch {
            repo.searchMovies(searchQuery).let { movie ->
                _searchMovie.value = listOf(movie)
            }
        }
    }
}