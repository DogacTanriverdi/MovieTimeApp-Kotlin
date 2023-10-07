package com.dogactnrvrdi.movietime.view.home_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogactnrvrdi.movietime.databinding.TopRatedMoviesItemRowBinding
import com.dogactnrvrdi.movietime.model.toprated.TopRatedResult
import com.dogactnrvrdi.movietime.util.Constants.BASE_IMAGE_URL

class TopRatedMoviesAdapter :
    RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {

    class TopRatedMoviesViewHolder(private val binding: TopRatedMoviesItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(topRatedMovies: TopRatedResult, context: Context) {
            binding.apply {
                tvMovieName.text = topRatedMovies.title
                Glide.with(context).load(BASE_IMAGE_URL + topRatedMovies.poster_path).into(ivPoster)
            }
        }
    }

    // Diff Util
    private val diffUtil = object : DiffUtil.ItemCallback<TopRatedResult>() {

        override fun areItemsTheSame(oldItem: TopRatedResult, newItem: TopRatedResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TopRatedResult, newItem: TopRatedResult): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<TopRatedResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        val binding = TopRatedMoviesItemRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TopRatedMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, holder.itemView.context)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(movie)
            }
        }
    }

    // On Item Click Listener
    private var onItemClickListener: ((TopRatedResult) -> Unit)? = null

    fun setOnItemClickListener(listener: (TopRatedResult) -> Unit) {
        onItemClickListener = listener
    }
}