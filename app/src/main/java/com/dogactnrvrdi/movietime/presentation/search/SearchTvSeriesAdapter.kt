package com.dogactnrvrdi.movietime.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogactnrvrdi.movietime.common.Constants.BASE_IMAGE_URL
import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.dogactnrvrdi.movietime.databinding.SearchTvSeriesItemRowBinding

class SearchTvSeriesAdapter :
    RecyclerView.Adapter<SearchTvSeriesAdapter.SearchTvSeriesViewHolder>() {

    class SearchTvSeriesViewHolder(
        private val binding: SearchTvSeriesItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvSeries: TvSeries, context: Context) {
            with(binding) {
                tvTvSeriesName.text = tvSeries.name
                Glide.with(context).load(BASE_IMAGE_URL + tvSeries.posterPath).into(ivPoster)
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TvSeries>() {

        override fun areItemsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var tvSeries: List<TvSeries>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTvSeriesViewHolder {
        val binding = SearchTvSeriesItemRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SearchTvSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int = tvSeries.size

    override fun onBindViewHolder(holder: SearchTvSeriesViewHolder, position: Int) {
        val series = tvSeries[position]
        with(holder) {
            bind(series, itemView.context)
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(series)
                }
            }
        }
    }

    private var onItemClickListener: ((TvSeries) -> Unit)? = null

    fun setOnItemClickListener(listener: (TvSeries) -> Unit) {
        onItemClickListener = listener
    }
}