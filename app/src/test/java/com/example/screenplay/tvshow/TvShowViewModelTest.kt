package com.example.screenplay.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.data.TvShowEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.data.source.remote.RemoteDataSource
import com.example.screenplay.data.source.remote.retrofit.NetworkConfig
import com.example.screenplay.ui.main.viewmodel.MovieViewModel
import com.example.screenplay.ui.main.viewmodel.TvShowViewModel
import com.example.screenplay.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.*
import java.util.ArrayList

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    private val networkConfig = NetworkConfig()
    private val remoteDataSource = RemoteDataSource.getInstance(networkConfig)

    @Spy
    @InjectMocks
    private val screenplayRepository: ScreenplayRepository = (ScreenplayRepository.getInstance(remoteDataSource))

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowViewModel(screenplayRepository)
    }

    @Test
    fun getTvShows(): Unit = runBlocking {
        Mockito.`when`(screenplayRepository.getMovies()).thenReturn(ArrayList<MovieEntity>())
        suspend {
            val tvShows = viewModel.getTvShows().value?.data
            verify<ScreenplayRepository>(screenplayRepository).getMovies()
            assertNotNull(tvShows)
            assertEquals(20, tvShows?.size)
        }
    }
}