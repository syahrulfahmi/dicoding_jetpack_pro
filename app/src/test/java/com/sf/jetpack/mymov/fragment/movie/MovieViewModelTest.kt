package com.sf.jetpack.mymov.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.network.datasource.MoviePagingSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.DummyData
import com.sf.jetpack.mymov.utils.FakeApi
import com.sf.jetpack.mymov.utils.MainCoroutineScopeRule
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val fakeApi = FakeApi()

    @Mock
    private lateinit var moviePagingRepository: IMoviePagingRepository

    @Mock
    private lateinit var roomRepository: IRoomRepository

    @Mock
    private lateinit var flow: Flow<PagingData<ListData>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(roomRepository, moviePagingRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get movies should be success`(): Unit = coroutineScope.runBlockingTest {
        val moviePagingSource = MoviePagingSource(fakeApi)
        val fakePagingData = Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { moviePagingSource }
        ).flow

        `when`(moviePagingRepository.getListMoviePaging()).thenReturn(fakePagingData)
        val moviesEntitites = viewModel.getListMoviePaging()
        val job = launch {
            viewModel.getListMoviePaging().collect {
            }
        }
//        assertNotNull(moviesEntities)
        job.cancel()
    }
}