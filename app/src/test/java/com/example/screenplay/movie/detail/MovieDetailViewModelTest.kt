package com.example.screenplay.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.screenplay.data.entity.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.ui.main.viewmodel.MovieDetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {
    private lateinit var viewModel: MovieDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var screenplayRepository: ScreenplayRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(screenplayRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MovieEntity(
                id = 88040,
                title = "Great Performances: Andrea Bocelli Live in Central Park",
                synopsis = "Widely regarded as the most popular Italian tenor in the world with more than 70 million albums sold, Bocelli was joined that rainy, windswept evening by pop stars Céline Dion, Tony Bennett, Chris Botti and David Foster, and from the classical world, bass baritone Bryn Terfel, sopranos Ana María Martínez and Pretty Yende, and violinist Nicola Benedetti, along with the Westminster Symphonic Choir, under the direction of Joe Miller. Bocelli presented a varied repertoire that includes well known arias, fan favorites, and some new surprises.  The first act of stirring operatic selections including Verdi and Puccini favorites gave way to more popular fare in the second including duets with Celine Dion and Tony Bennett.",
                voteRating = 0.0
        )
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(screenplayRepository.getMovieDetail(88040)).thenReturn(movie)
        val movieEntity = viewModel.getMovieDetail(88040).value
        Mockito.verify(screenplayRepository).getMovieDetail(88040)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.synopsis, movieEntity?.synopsis)
        assertEquals(dummyMovie.voteRating, movieEntity?.voteRating)

        viewModel.getMovieDetail(88040).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}