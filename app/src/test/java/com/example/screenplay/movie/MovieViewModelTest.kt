package com.example.screenplay.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.data.source.remote.RemoteDataSource
import com.example.screenplay.data.source.remote.retrofit.NetworkConfig
import com.example.screenplay.ui.main.viewmodel.MovieViewModel
import com.example.screenplay.utils.Resource
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*


@ExperimentalCoroutinesApi
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val networkConfig = NetworkConfig()
    private val remoteDataSource = RemoteDataSource.getInstance(networkConfig)

    @Spy
    @InjectMocks
    private val screenplayRepository: ScreenplayRepository = (ScreenplayRepository.getInstance(remoteDataSource))

    @Mock
    private lateinit var observer: Observer<Resource<ArrayList<MovieEntity>>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(screenplayRepository)
    }

    @Test
    fun getMovies(){
        viewModel.getMovies().observeForever(observer)

    }
}