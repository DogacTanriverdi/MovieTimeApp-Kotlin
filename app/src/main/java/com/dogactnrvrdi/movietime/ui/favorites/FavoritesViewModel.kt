package com.dogactnrvrdi.movietime.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    val movies = repo.getAllMovies()

    fun deleteMovie(movie: Movie) =
        viewModelScope.launch {
            repo.deleteMovie(movie)
        }
}