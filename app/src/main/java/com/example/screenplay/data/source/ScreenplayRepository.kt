package com.example.screenplay.data.source

import com.example.screenplay.data.*
import com.example.screenplay.data.source.remote.RemoteDataSource

class ScreenplayRepository private constructor(private val remoteDataSource: RemoteDataSource): ScreenplayDataSource {
    companion object {
        @Volatile
        private var instance: ScreenplayRepository? = null
        fun getInstance(remoteData: RemoteDataSource): ScreenplayRepository =
                instance ?: synchronized(this) {
                    instance ?: ScreenplayRepository(remoteData)
                }
    }

    override suspend fun getMovies(): ArrayList<MovieEntity> {
        val moviesResponse = remoteDataSource.getMovies().results
        val movieList = ArrayList<MovieEntity>()
        if(moviesResponse != null) {
            for (response in moviesResponse) {
                val movie = MovieEntity(
                    id = response.id,
                    title = response.title,
                    posterPath = "https://image.tmdb.org/t/p/w185${response.posterPath}",
                    synopsis = response.overview,
                    voteRating = response.voteAverage
                )
                movieList.add(movie)
            }
        }
        return movieList
    }

    override suspend fun getMovieDetail(id: Int): MovieEntity {
        val movieResponse = remoteDataSource.getMovieDetail(id)
        val movie = MovieEntity(
            id = movieResponse.id,
            title = movieResponse.title,
            synopsis = movieResponse.overview,
            posterPath = "https://image.tmdb.org/t/p/w185${movieResponse.posterPath}",
            releaseDate = movieResponse.releaseDate,
            voteRating = movieResponse.voteAverage,
        )
        if(movieResponse.genres != null){
            for(genreResponse in movieResponse.genres){
                val genre = GenreEntity(
                    id = genreResponse.id,
                    name = genreResponse.name
                )
                movie.genres?.add(genre)
            }
        }
        if(movieResponse.productionCompanies != null){
            for(productionCompanyResponse in movieResponse.productionCompanies){
                val productionCompany = ProductionCompanyEntity(
                    id = productionCompanyResponse.id,
                    name = productionCompanyResponse.name
                )
                movie.productionCompanies?.add(productionCompany)
            }
        }

        return movie
    }

    override suspend fun getTvShows(): ArrayList<TvShowEntity> {
        val tvShowsResponse = remoteDataSource.getTvShows().results
        val tvShowList = ArrayList<TvShowEntity>()
        if(tvShowsResponse != null) {
            for (response in tvShowsResponse) {
                val tvShow = TvShowEntity(
                    id = response.id,
                    title = response.name,
                    posterPath = "https://image.tmdb.org/t/p/w185${response.posterPath}",
                    synopsis = response.overview,
                    voteRating = response.voteAverage,
                )
                tvShowList.add(tvShow)
            }
        }
        return tvShowList
    }

    override suspend fun getTvShowDetail(id: Int): TvShowEntity {
        val tvShowResponse = remoteDataSource.getTvShowDetail(id)
        val tvShow = TvShowEntity(
            id = tvShowResponse.id,
            title = tvShowResponse.name,
            synopsis = tvShowResponse.overview,
            posterPath = "https://image.tmdb.org/t/p/w185${tvShowResponse.posterPath}",
            releaseDate = tvShowResponse.firstAirDate,
            voteRating = tvShowResponse.voteAverage,
        )
        if(tvShowResponse.genres != null){
            for(genreResponse in tvShowResponse.genres){
                val genre = GenreEntity(
                    id = genreResponse.id,
                    name = genreResponse.name
                )
                tvShow.genres?.add(genre)
            }
        }
        if(tvShowResponse.productionCompanies != null){
            for(productionCompanyResponse in tvShowResponse.productionCompanies){
                val productionCompany = ProductionCompanyEntity(
                    id = productionCompanyResponse.id,
                    name = productionCompanyResponse.name
                )
                tvShow.productionCompanies?.add(productionCompany)
            }
        }

        if(tvShowResponse.seasons != null){
            for(productionCompanyResponse in tvShowResponse.seasons){
                val season = SeasonEntity(
                    id = productionCompanyResponse.id,
                    name = productionCompanyResponse.name
                )
                tvShow.seasons?.add(season)
            }
            tvShow.nSeasons = tvShow.seasons?.size
        }

        return tvShow
    }
}