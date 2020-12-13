package com.example.screenplay.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.screenplay.data.entity.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.ui.main.viewmodel.MovieViewModel
import com.example.screenplay.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var screenplayRepository: ScreenplayRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(screenplayRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.dummyMovies
        val movies = MutableLiveData<ArrayList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(screenplayRepository.getMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(screenplayRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}