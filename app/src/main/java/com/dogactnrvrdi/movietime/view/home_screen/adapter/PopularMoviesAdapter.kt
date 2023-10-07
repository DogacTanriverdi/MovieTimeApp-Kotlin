package com.dogactnrvrdi.movietime.view.home_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogactnrvrdi.movietime.databinding.PopularMoviesItemRowBinding
import com.dogactnrvrdi.movietime.model.popular.PopularResult
import com.dogactnrvrdi.movietime.util.Constants.BASE_IMAGE_URL

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    class PopularMoviesViewHolder(
        private val binding: PopularMoviesItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(popularMovies: PopularResult, context: Context) {
            binding.apply {
                tvMovieName.text = popularMovies.title
                Glide.with(context)
                    .load(BASE_IMAGE_URL + popularMovies.poster_path)
                    .into(ivPoster)
            }
        }
    }

    // Diff Util
    private val diffUtil = object : DiffUtil.ItemCallback<PopularResult>() {

        override fun areItemsTheSame(oldItem: PopularResult, newItem: PopularResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularResult, newItem: PopularResult): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<PopularResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val binding = PopularMoviesItemRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PopularMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, holder.itemView.context)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(movie)
            }
        }
    }

    // On Item Click Listener
    private var onItemClickListener: ((PopularResult) -> Unit)? = null

    fun setOnItemClickListener(listener: (PopularResult) -> Unit) {
        onItemClickListener = listener
    }
}