package com.dogactnrvrdi.movietime.presentation.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogactnrvrdi.movietime.common.Constants.BASE_IMAGE_URL
import com.dogactnrvrdi.movietime.data.model.Movie
import com.dogactnrvrdi.movietime.databinding.DefaultItemRowBinding

class FavoriteMoviesAdapter : RecyclerView.Adapter<FavoriteMoviesAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(
        private val binding: DefaultItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, context: Context) {
            binding.apply {
                tvName.text = movie.title
                Glide.with(context)
                    .load(BASE_IMAGE_URL + movie.posterPath)
                    .into(ivPoster)
            }
        }
    }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = DefaultItemRowBinding.inflate(
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

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }
}


