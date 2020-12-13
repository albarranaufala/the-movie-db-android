package com.example.screenplay.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.screenplay.data.entity.*
import com.example.screenplay.data.source.remote.RemoteDataSource
import com.example.screenplay.data.source.remote.retrofit.response.MovieDetailResponse
import com.example.screenplay.data.source.remote.retrofit.response.MovieListResponse
import com.example.screenplay.data.source.remote.retrofit.response.TvShowDetailResponse
import com.example.screenplay.data.source.remote.retrofit.response.TvShowListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScreenplayRepository private constructor(private val remoteDataSource: RemoteDataSource): ScreenplayDataSource {
    companion object {
        @Volatile
        private var instance: ScreenplayRepository? = null
        fun getInstance(remoteData: RemoteDataSource): ScreenplayRepository =
                instance ?: synchronized(this) {
                    instance ?: ScreenplayRepository(remoteData)
                }
    }

    override fun getMovies(): LiveData<ArrayList<MovieEntity>> {
        val movieResults = MutableLiveData<ArrayList<MovieEntity>>()
        remoteDataSource.getMovies().enqueue(object: Callback<MovieListResponse>{
            override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {
                if(response.isSuccessful){
                    val movieList = ArrayList<MovieEntity>()
                    response.body()?.results.let {
                        if (it != null) {
                            for (item in it) {
                                val movie = MovieEntity(
                                        id = item.id,
                                        title = item.title,
                                        posterPath = "https://image.tmdb.org/t/p/w185${item.posterPath}",
                                        synopsis = item.overview,
                                        voteRating = item.voteAverage
                                )
                                movieList.add(movie)
                            }
                        }
                        movieResults.postValue(movieList)
                    }
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                movieResults.postValue(null)
            }
        })
        return movieResults
    }

    override fun getMovieDetail(id: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovieDetail(id).enqueue(object: Callback<MovieDetailResponse>{
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        val movie = MovieEntity(
                                id = it.id,
                                title = it.title,
                                synopsis = it.overview,
                                posterPath = "https://image.tmdb.org/t/p/w185${it.posterPath}",
                                releaseDate = it.releaseDate,
                                voteRating = it.voteAverage,
                        )
                        if(it.genres != null){
                            for(genreResponse in it.genres){
                                val genre = GenreEntity(
                                        id = genreResponse.id,
                                        name = genreResponse.name
                                )
                                movie.genres?.add(genre)
                            }
                        }
                        if(it.productionCompanies != null){
                            for(productionCompanyResponse in it.productionCompanies){
                                val productionCompany = ProductionCompanyEntity(
                                        id = productionCompanyResponse.id,
                                        name = productionCompanyResponse.name
                                )
                                movie.productionCompanies?.add(productionCompany)
                            }
                        }
                        movieResult.postValue(movie)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                movieResult.postValue(null)
            }
        })
        return movieResult
    }

    override fun getTvShows(): LiveData<ArrayList<TvShowEntity>> {
        val tvShowResults = MutableLiveData<ArrayList<TvShowEntity>>()
        remoteDataSource.getTvShows().enqueue(object : Callback<TvShowListResponse>{
            override fun onResponse(call: Call<TvShowListResponse>, response: Response<TvShowListResponse>) {
                if(response.isSuccessful){
                    val tvShowList = ArrayList<TvShowEntity>()
                    response.body()?.results.let {
                        if (it != null) {
                            for (item in it) {
                                val tvShow = TvShowEntity(
                                        id = item.id,
                                        title = item.name,
                                        posterPath = "https://image.tmdb.org/t/p/w185${item.posterPath}",
                                        synopsis = item.overview,
                                        voteRating = item.voteAverage,
                                )
                                tvShowList.add(tvShow)
                            }
                        }
                        tvShowResults.postValue(tvShowList)
                    }
                }
            }

            override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
                tvShowResults.postValue(null)
            }
        })
        return tvShowResults
    }

    override fun getTvShowDetail(id: Int): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShowDetail(id).enqueue(object: Callback<TvShowDetailResponse>{
            override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        val tvShow = TvShowEntity(
                                id = it.id,
                                title = it.name,
                                synopsis = it.overview,
                                posterPath = "https://image.tmdb.org/t/p/w185${it.posterPath}",
                                releaseDate = it.firstAirDate,
                                voteRating = it.voteAverage,
                        )
                        if(it.genres != null){
                            for(genreResponse in it.genres){
                                val genre = GenreEntity(
                                        id = genreResponse.id,
                                        name = genreResponse.name
                                )
                                tvShow.genres?.add(genre)
                            }
                        }
                        if(it.productionCompanies != null){
                            for(productionCompanyResponse in it.productionCompanies){
                                val productionCompany = ProductionCompanyEntity(
                                        id = productionCompanyResponse.id,
                                        name = productionCompanyResponse.name
                                )
                                tvShow.productionCompanies?.add(productionCompany)
                            }
                        }
                        if(it.seasons != null){
                            for(productionCompanyResponse in it.seasons){
                                val season = SeasonEntity(
                                        id = productionCompanyResponse.id,
                                        name = productionCompanyResponse.name
                                )
                                tvShow.seasons?.add(season)
                            }
                            tvShow.nSeasons = tvShow.seasons?.size
                        }
                        tvShowResult.postValue(tvShow)
                    }
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                tvShowResult.postValue(null)
            }
        })
        return tvShowResult
    }
}