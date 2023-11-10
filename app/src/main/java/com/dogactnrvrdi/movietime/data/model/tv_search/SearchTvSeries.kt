package com.dogactnrvrdi.movietime.data.model.tv_search

import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.google.gson.annotations.SerializedName

data class SearchTvSeries(
    val page: Int,
    val results: List<TvSeries>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)