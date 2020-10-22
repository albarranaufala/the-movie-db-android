package com.example.screenplay.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.di.Injection
import com.example.screenplay.ui.main.viewmodel.MovieDetailViewModel
import com.example.screenplay.ui.main.viewmodel.MovieViewModel
import com.example.screenplay.ui.main.viewmodel.TvShowDetailViewModel
import com.example.screenplay.ui.main.viewmodel.TvShowViewModel

class ViewModelFactory private constructor(private val mScreenplayRepository: ScreenplayRepository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository())
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mScreenplayRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(mScreenplayRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mScreenplayRepository) as T
            }
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> {
                TvShowDetailViewModel(mScreenplayRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}