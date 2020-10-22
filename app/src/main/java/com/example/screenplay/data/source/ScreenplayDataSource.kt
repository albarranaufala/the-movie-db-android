package com.example.screenplay.data.source

import com.example.screenplay.data.MovieEntity
import com.example.screenplay.data.TvShowEntity

interface ScreenplayDataSource {
    suspend fun getMovies(): ArrayList<MovieEntity>

    suspend fun getMovieDetail(id: Int): MovieEntity

    suspend fun getTvShows(): ArrayList<TvShowEntity>

    suspend fun getTvShowDetail(id: Int): TvShowEntity
}