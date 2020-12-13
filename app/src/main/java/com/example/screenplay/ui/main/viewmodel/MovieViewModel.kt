package com.example.screenplay.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.screenplay.data.entity.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository

class MovieViewModel(private val screenplayRepository: ScreenplayRepository): ViewModel() {
    fun getMovies(): LiveData<ArrayList<MovieEntity>> = screenplayRepository.getMovies()
}