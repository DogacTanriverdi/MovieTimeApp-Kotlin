package com.dogactnrvrdi.movietime.view.favorites_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogactnrvrdi.movietime.databinding.FavoriteMoviesItemRowBinding
import com.dogactnrvrdi.movietime.model.movie_details.MovieDetails
import com.dogactnrvrdi.movietime.model.popular.PopularResult
import com.dogactnrvrdi.movietime.util.Constants.BASE_IMAGE_URL

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(
        private val binding: FavoriteMoviesItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieDetails, context: Context) {
            binding.apply {
                tvMovieName.text = movie.title
                Glide.with(context)
                    .load(BASE_IMAGE_URL + movie.poster_path)
                    .into(ivPoster)
            }
        }
    }

    // Diff Util
    private val diffUtil = object : DiffUtil.ItemCallback<MovieDetails>() {

        override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<MovieDetails>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoriteMoviesItemRowBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return FavoritesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, holder.itemView.context)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(movie)
            }
        }
    }

    // On Item Click Listener
    private var onItemClickListener: ((MovieDetails) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieDetails) -> Unit) {
        onItemClickListener = listener
    }
}


