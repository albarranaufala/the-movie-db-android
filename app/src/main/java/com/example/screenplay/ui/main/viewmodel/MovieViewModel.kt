package com.example.screenplay.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val screenplayRepository: ScreenplayRepository): ViewModel() {

    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = screenplayRepository.getMovies()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}