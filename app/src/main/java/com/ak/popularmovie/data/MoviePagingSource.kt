package com.ak.popularmovie.data

import androidx.paging.PagingSource
import com.ak.popularmovie.api.MovieService
import com.ak.popularmovie.model.Movie
import com.ak.popularmovie.model.MovieType
import retrofit2.HttpException
import java.io.IOException

const val START_PAGE = 1

class MoviePagingSource(
    private val service : MovieService,
    private val movieType : MovieType
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val page = params.key ?: START_PAGE

        return try {

            val response = when(movieType) {
                MovieType.NOW_PLAYING -> {
                    service.getNowPlayingMovies(page = page)
                }
                MovieType.UPCOMING -> {
                    service.getUpcomingMovies(page = page)
                }
                MovieType.TOP_RATED -> {
                    service.getTopRatedMovies(page = page)
                }
            }

            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if(page == START_PAGE) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )

        } catch (e : IOException) {
            LoadResult.Error(e)
        } catch (e : HttpException) {
            LoadResult.Error(e)
        }

    }
}