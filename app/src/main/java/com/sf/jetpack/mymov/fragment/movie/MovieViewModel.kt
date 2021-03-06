package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.MovieResponse


class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    fun getListMovieFromApi(): LiveData<MovieResponse> = movieRepository.getListMovie()
}