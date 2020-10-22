package com.example.screenplay.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.screenplay.data.TvShowEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.utils.DataDummy
import com.example.screenplay.utils.Resource
import kotlinx.coroutines.Dispatchers

class TvShowDetailViewModel(private val screenplayRepository: ScreenplayRepository): ViewModel() {
    fun getTvShowDetail(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = screenplayRepository.getTvShowDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}