package com.ak.popularmovie.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ak.popularmovie.api.MovieService
import com.ak.popularmovie.model.Movie
import com.ak.popularmovie.model.MovieDetail
import com.ak.popularmovie.model.MovieType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service : MovieService
) {

    @ExperimentalPagingApi
    fun getMoviesByType(movieType : MovieType) : Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(service,movieType) }
        ).flow
    }
//
//    @ExperimentalPagingApi
//    fun getMoviesList() : Flow<PagingData<Movie>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 1,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { MoviePagingSource(service) }
//        ).flow
//    }
//
//    @ExperimentalPagingApi
//    fun getMoviesList() : Flow<PagingData<Movie>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 1,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { MoviePagingSource(service) }
//        ).flow
//    }

    suspend fun getMovieById(movieId : Int) : MovieDetail? {

        return try {
            val response = service.getMovie(movieId)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                null
            }
        } catch (e : Exception) {
            e.printStackTrace()
            null
        }
    }

}