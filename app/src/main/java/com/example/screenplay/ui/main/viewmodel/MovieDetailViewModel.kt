package com.example.screenplay.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.utils.DataDummy
import com.example.screenplay.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(private val screenplayRepository: ScreenplayRepository): ViewModel() {
    fun getMovieDetail(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = screenplayRepository.getMovieDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}