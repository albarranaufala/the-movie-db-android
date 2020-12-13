package com.example.screenplay.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.screenplay.data.entity.TvShowEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.ui.main.viewmodel.TvShowViewModel
import com.example.screenplay.utils.DataDummy
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.ArrayList

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var screenplayRepository: ScreenplayRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<TvShowEntity>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(screenplayRepository)
    }

    @Test
    fun getTvShows(): Unit = runBlocking {
        val dummyTvShows = DataDummy.dummyTvShows
        val tvShows = MutableLiveData<ArrayList<TvShowEntity>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(screenplayRepository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value
        Mockito.verify(screenplayRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}