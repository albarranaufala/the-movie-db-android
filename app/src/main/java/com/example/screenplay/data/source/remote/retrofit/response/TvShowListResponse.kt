package com.example.screenplay.data.source.remote.retrofit.response

import com.google.gson.annotations.SerializedName

data class TvShowListResponse(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<TvShowItem>? = null
)

data class TvShowItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
)