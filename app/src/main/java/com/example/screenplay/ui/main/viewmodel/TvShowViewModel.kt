package com.example.screenplay.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.screenplay.data.entity.TvShowEntity
import com.example.screenplay.data.source.ScreenplayRepository

class TvShowViewModel(private val screenplayRepository: ScreenplayRepository): ViewModel() {
    fun getTvShows(): LiveData<ArrayList<TvShowEntity>> = screenplayRepository.getTvShows()
}