package com.dogactnrvrdi.movietime.view.favorites_screen.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietime.R
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.repo.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repo: IMovieRepository
) : ViewModel() {

    val movies = repo.getAllMovies()

    fun deleteMovie(context: Context, movie: MovieDetails) =
        viewModelScope.launch {
            repo.deleteMovie(movie)
            Toast.makeText(
                context,
                context.getString(R.string.movie_deleted_successfully),
                Toast.LENGTH_LONG
            ).show()
        }
}