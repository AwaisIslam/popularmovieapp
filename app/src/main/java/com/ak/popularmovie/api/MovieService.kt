package com.ak.popularmovie.api

import com.ak.popularmovie.BuildConfig
import com.ak.popularmovie.model.MovieDetail
import com.ak.popularmovie.model.MovieResponse
import com.ak.popularmovie.model.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(
        @Query("page")
        page : Int = 1 ) : MovieResponse

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(
        @Query("page")
        page : Int = 1 ) : MovieResponse

    @GET("movie/top_rated?api_key=$API_KEY")
    suspend fun getTopRatedMovies(
        @Query("page")
        page : Int = 1 ) : MovieResponse

    @GET("movie/{movieId}?api_key=$API_KEY")
    suspend fun getMovie(@Path("movieId") movieId : Int) : Response<MovieDetail>


    // https://www.youtube.com/watch?v=BdJKm16Co6M
    @GET("movie/{movieId}/videos?api_key=$API_KEY")
    suspend fun getMovieVideo(@Path("movieId") movieId: Int) : Response<MovieVideoResponse>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.API_KEY
    }
}

sealed class DataResult<out T> {

    object Loading : DataResult<Nothing>()
    data class Success<T>(val result : T) : DataResult<T>()
    data class Error(val error : String) : DataResult<Nothing>()
}