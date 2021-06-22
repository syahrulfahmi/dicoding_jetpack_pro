package com.sf.jetpack.mymov.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.response.ListData
import kotlinx.coroutines.flow.Flow

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */
class MoviePagingRepository(private val movieApi: MovieDataSource) : IMoviePagingRepository {
    override fun getListMoviePaging(): Flow<PagingData<ListData>> {
       return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieApi) }
        ).flow
    }
}