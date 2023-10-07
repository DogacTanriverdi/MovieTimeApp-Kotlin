package com.dogactnrvrdi.movietime.view.home_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogactnrvrdi.movietime.databinding.UpcomingMoviesItemRowBinding
import com.dogactnrvrdi.movietime.model.upcoming.UpcomingResult
import com.dogactnrvrdi.movietime.util.Constants.BASE_IMAGE_URL

class UpcomingMoviesAdapter :
    RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>() {

    class UpcomingMoviesViewHolder(
        private val binding: UpcomingMoviesItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(upcomingMovies: UpcomingResult, context: Context) {
            binding.apply {
                tvMovieName.text = upcomingMovies.title
                tvMovieReleaseDate.text = upcomingMovies.release_date
                Glide.with(context)
                    .load(BASE_IMAGE_URL + upcomingMovies.poster_path)
                    .into(ivPoster)
            }
        }
    }

    // Diff Util
    private val diffUtil = object : DiffUtil.ItemCallback<UpcomingResult>() {

        override fun areItemsTheSame(oldItem: UpcomingResult, newItem: UpcomingResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UpcomingResult, newItem: UpcomingResult): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<UpcomingResult>
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
    private var onItemClickListener: ((UpcomingResult) -> Unit)? = null

    fun setOnItemClickListener(listener: (UpcomingResult) -> Unit) {
        onItemClickListener = listener
    }
}