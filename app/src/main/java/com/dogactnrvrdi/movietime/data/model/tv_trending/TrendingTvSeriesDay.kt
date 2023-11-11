package com.dogactnrvrdi.movietime.data.model.tv_trending

import com.dogactnrvrdi.movietime.data.model.TvSeries
import com.google.gson.annotations.SerializedName

data class TrendingTvSeriesDay(
    val page: Int,
    val results: List<TvSeries>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
