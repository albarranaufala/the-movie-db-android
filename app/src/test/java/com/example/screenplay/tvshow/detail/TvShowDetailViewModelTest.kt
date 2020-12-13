package com.example.screenplay.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.screenplay.data.entity.TvShowEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.ui.main.viewmodel.TvShowDetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {
    private lateinit var viewModel: TvShowDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var screenplayRepository: ScreenplayRepository

    @Mock
    private lateinit var observer: Observer<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(screenplayRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = TvShowEntity(
                id = 88040,
                title = "Given",
                synopsis = "Mafuyu tells Ritsuka that he likes him and Ritsuka is ecstatic to find out that they both like each other but at the same time, he remembers himself saying that band members should never bring relationships into the band. However, they both decide to talk to Haruki and Akihiko about it. Haruki gives them a lecture and Akihiko congratulates them. Meanwhile, Haruki, who likes Akihiko...",
                voteRating = 0.0
        )
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        Mockito.`when`(screenplayRepository.getTvShowDetail(88040)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShowDetail(88040).value
        Mockito.verify(screenplayRepository).getTvShowDetail(88040)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity?.id)
        assertEquals(dummyTvShow.title, tvShowEntity?.title)
        assertEquals(dummyTvShow.synopsis, tvShowEntity?.synopsis)
        assertEquals(dummyTvShow.voteRating, tvShowEntity?.voteRating)

        viewModel.getTvShowDetail(88040).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}