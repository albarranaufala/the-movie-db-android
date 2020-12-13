package com.example.screenplay.data.source

import androidx.lifecycle.LiveData
import com.example.screenplay.data.entity.MovieEntity
import com.example.screenplay.data.entity.TvShowEntity

interface ScreenplayDataSource {
    fun getMovies(): LiveData<ArrayList<MovieEntity>>

    fun getMovieDetail(id: Int): LiveData<MovieEntity>

    fun getTvShows(): LiveData<ArrayList<TvShowEntity>>

    fun getTvShowDetail(id: Int): LiveData<TvShowEntity>
}