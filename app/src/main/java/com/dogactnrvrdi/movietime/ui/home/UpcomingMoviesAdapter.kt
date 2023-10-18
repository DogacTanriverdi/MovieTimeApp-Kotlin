package com.dogactnrvrdi.movietime.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogactnrvrdi.movietime.databinding.UpcomingMoviesItemRowBinding
import com.dogactnrvrdi.movietime.common.Constants.BASE_IMAGE_URL
import com.dogactnrvrdi.movietime.data.model.Movie

class UpcomingMoviesAdapter :
    RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>() {

    class UpcomingMoviesViewHolder(
        private val binding: UpcomingMoviesItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(upcomingMovies: Movie, context: Context) {
            binding.apply {
                tvMovieName.text = upcomingMovies.title
                tvMovieReleaseDate.text = upcomingMovies.releaseDate
                Glide.with(context)
                    .load(BASE_IMAGE_URL + upcomingMovies.posterPath)
                    .into(ivPoster)
            }
        }
    }

    // Diff Util
    private val diffUtil = object : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<Movie>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        val binding = UpcomingMoviesItemRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UpcomingMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, holder.itemView.context)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(movie)
            }
        }
    }

    // On Item Click Listener
    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }
}