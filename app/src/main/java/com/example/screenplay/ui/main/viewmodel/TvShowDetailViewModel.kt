package com.example.screenplay.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.screenplay.data.entity.TvShowEntity
import com.example.screenplay.data.source.ScreenplayRepository

class TvShowDetailViewModel(private val screenplayRepository: ScreenplayRepository): ViewModel() {
    fun getTvShowDetail(id: Int):LiveData<TvShowEntity> = screenplayRepository.getTvShowDetail(id)
}