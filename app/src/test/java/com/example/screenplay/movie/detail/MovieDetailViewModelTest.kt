package com.example.screenplay.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.data.source.ScreenplayRepository
import com.example.screenplay.data.source.remote.RemoteDataSource
import com.example.screenplay.data.source.remote.retrofit.NetworkConfig
import com.example.screenplay.ui.main.viewmodel.MovieDetailViewModel
import com.example.screenplay.utils.DataDummy
import com.example.screenplay.utils.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.*

class MovieDetailViewModelTest {
    private lateinit var viewModel: MovieDetailViewModel

    private val networkConfig = NetworkConfig()
    private val remoteDataSource = RemoteDataSource.getInstance(networkConfig)

    @Spy
    @InjectMocks
    private val screenplayRepository: ScreenplayRepository = (ScreenplayRepository.getInstance(remoteDataSource))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieDetailViewModel(screenplayRepository)
    }

    @Test
    fun getMovieDetail(): Unit = runBlocking {
        val movieDummy = MovieEntity(
                id = 88040,
                title = "Great Performances: Andrea Bocelli Live in Central Park",
                synopsis = "Widely regarded as the most popular Italian tenor in the world with more than 70 million albums sold, Bocelli was joined that rainy, windswept evening by pop stars Céline Dion, Tony Bennett, Chris Botti and David Foster, and from the classical world, bass baritone Bryn Terfel, sopranos Ana María Martínez and Pretty Yende, and violinist Nicola Benedetti, along with the Westminster Symphonic Choir, under the direction of Joe Miller. Bocelli presented a varied repertoire that includes well known arias, fan favorites, and some new surprises.  The first act of stirring operatic selections including Verdi and Puccini favorites gave way to more popular fare in the second including duets with Celine Dion and Tony Bennett.",
                voteRating = 0.0
        )
        Mockito.`when`(screenplayRepository.getMovieDetail(88040)).thenReturn(MovieEntity(id = 88040))
        suspend {
            val movieEntity = viewModel.getMovieDetail(88040).value?.data
            assertNotNull(movieEntity)
            assertEquals(movieDummy.id, movieEntity?.id)
            assertEquals(movieDummy.title, movieEntity?.title)
            assertEquals(movieDummy.synopsis, movieEntity?.synopsis)
            assertEquals(movieDummy.voteRating, movieEntity?.voteRating)
        }
    }
}