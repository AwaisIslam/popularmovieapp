package com.ak.popularmovie.data

import com.ak.popularmovie.api.DataResult
import com.ak.popularmovie.api.MovieService
import com.ak.popularmovie.model.MovieDetail
import com.ak.popularmovie.model.MovieVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val service : MovieService) {

    fun getMovieById(movieId : Int) : Flow<DataResult<MovieDetail>>  {

        return flow {
            emit(DataResult.Loading)

            try {
                val apiResponse = service.getMovie(movieId)
                if (apiResponse.isSuccessful) {

                    apiResponse.body()?.let {
                        emit(DataResult.Success(it))
                    } ?: emit(DataResult.Error("Data Empty"))

                } else {
                    emit(DataResult.Error(apiResponse.errorBody().toString()))
                }

            } catch (e : Exception) {
                emit(DataResult.Error(e.message ?: "Unknown Error"))
            }

        }.flowOn(Dispatchers.IO)
    }


    suspend fun getMovieVideo(movieId : Int) : MovieVideo? {

        return try {
            val apiResponse = service.getMovieVideo(movieId)
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let {
                    if (!it.results.isNullOrEmpty()) {
                        it.results[0]

                    } else null
                }

            } else {
                null
            }
        } catch (e : Exception) {
            e.printStackTrace()
            null
        }
    }
}