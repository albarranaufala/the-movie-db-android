package com.example.screenplay.tvshow.detail
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.data.TvShowEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.ui.main.viewmodel.MovieDetailViewModel
import com.example.screenplay.ui.main.viewmodel.TvShowDetailViewModel
import com.example.screenplay.utils.DataDummy
import com.example.screenplay.utils.Resource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock

class TvShowDetailViewModelTest {
    private lateinit var viewModel: TvShowDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var screenplayRepository: ScreenplayRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(screenplayRepository)
    }

    @Test
    fun getTvShow() {
        val tvShowDummy = TvShowEntity(
                id = 88040,
                title = "Given",
                synopsis = "Mafuyu tells Ritsuka that he likes him and Ritsuka is ecstatic to find out that they both like each other but at the same time, he remembers himself saying that band members should never bring relationships into the band. However, they both decide to talk to Haruki and Akihiko about it. Haruki gives them a lecture and Akihiko congratulates them. Meanwhile, Haruki, who likes Akihiko...",
                voteRating = 0.0
        )
        val tvShowEntity = viewModel.getTvShowDetail(88040).value?.data
        assertNotNull(tvShowEntity)
        assertEquals(tvShowDummy.id, tvShowEntity?.id)
        assertEquals(tvShowDummy.title, tvShowEntity?.title)
        assertEquals(tvShowDummy.synopsis, tvShowEntity?.synopsis)
        assertEquals(tvShowDummy.voteRating, tvShowEntity?.voteRating)
    }
}