package com.example.screenplay.data.entity

data class TvShowEntity (
        var id: Int,
        var title: String? = null,
        var posterPath: String? = null,
        var poster: Int? = null,
        var creator: String? = null,
        var releaseDate: String? = null,
        var voteRating: Double? = 0.0,
        var releaseYear: Int? = null,
        var genre: String? = null,
        var genres: ArrayList<GenreEntity>? = ArrayList(),
        var productionCompanies: ArrayList<ProductionCompanyEntity>? = ArrayList(),
        var seasons: ArrayList<SeasonEntity>? = ArrayList(),
        var synopsis: String? = null,
        var nSeasons: Int? = null,
)