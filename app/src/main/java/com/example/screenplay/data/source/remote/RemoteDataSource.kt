package com.example.screenplay.data.source.remote

import com.example.screenplay.data.source.remote.retrofit.NetworkConfig

class RemoteDataSource private constructor(private val networkConfig: NetworkConfig){
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(networkConfig: NetworkConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(networkConfig)
            }
    }

    fun getMovies() = networkConfig.getService().getMovies()

    fun getMovieDetail(id: Int) = networkConfig.getService().getMovieDetail(id)

    fun getTvShows() = networkConfig.getService().getTvShows()

    fun getTvShowDetail(id: Int) = networkConfig.getService().getTvShowDetail(id)
}