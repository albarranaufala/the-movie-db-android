package com.example.screenplay.data.entity

data class MovieEntity (
        var id: Int,
        var title: String? = null,
        var posterPath: String? = null,
        var poster: Int? = null,
        var director: String? = null,
        var releaseDate: String? = null,
        var releaseYear: Int? = null,
        var synopsis: String? = null,
        var voteRating: Double? = 0.0,
        var genres: ArrayList<GenreEntity>? = ArrayList(),
        var productionCompanies: ArrayList<ProductionCompanyEntity>? = ArrayList()
)