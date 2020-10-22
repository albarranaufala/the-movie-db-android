package com.example.screenplay.data.source.remote.retrofit.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = 0.0,

    @field:SerializedName("genres")
    val genres: List<GenreItem>? = null,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyItem>? = null,

    @field:SerializedName("seasons")
    val seasons: List<SeasonItem>? = null,
)

data class SeasonItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String? = null,
)