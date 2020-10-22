package com.example.screenplay.data.source.remote.retrofit.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieItem>? = null
)

data class MovieItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
)