package com.ak.popularmovie.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ak.popularmovie.data.MovieRepository
import com.ak.popularmovie.model.Movie
import com.ak.popularmovie.model.MovieType
import kotlinx.coroutines.flow.Flow

class MovieViewModel @ViewModelInject constructor(private val repository : MovieRepository) : ViewModel() {

    private var currentMovieType = MovieType.NOW_PLAYING
    private var currentMovies : Flow<PagingData<Movie>>? = null

    @ExperimentalPagingApi
    fun getMoviesByType(newMovieType : MovieType) : Flow<PagingData<Movie>> {

        val latestResult = currentMovies
        if (currentMovieType == newMovieType && latestResult != null) {
            return latestResult
        }

        currentMovieType = newMovieType
        val newResult = repository.getMoviesByType(newMovieType)
            .cachedIn(viewModelScope)
        currentMovies = newResult
        return newResult
    }
}
