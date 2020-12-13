package com.example.screenplay.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.screenplay.data.entity.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository

class MovieDetailViewModel(private val screenplayRepository: ScreenplayRepository): ViewModel() {
    fun getMovieDetail(id: Int): LiveData<MovieEntity> = screenplayRepository.getMovieDetail(id)
}