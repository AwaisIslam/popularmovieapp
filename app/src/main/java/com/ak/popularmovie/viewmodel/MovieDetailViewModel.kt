package com.ak.popularmovie.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ak.popularmovie.api.DataResult
import com.ak.popularmovie.data.MovieDetailRepository
import com.ak.popularmovie.model.MovieDetail
import com.ak.popularmovie.model.MovieVideo
import com.ak.popularmovie.utils.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val repository : MovieDetailRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId : Int by lazy {
        savedStateHandle.get<Int>("movie_id")!!
    }

    private val _eventMovieVideo = MutableLiveData<Event<MovieVideo>>()
    val eventMovieVideo : LiveData<Event<MovieVideo>>
        get() = _eventMovieVideo

    val movieDetailResult : LiveData<DataResult<MovieDetail>> = liveData {
        repository.getMovieById(movieId)
            .collect { emit(it) }
    }

    fun openMovieTrailer() {
        viewModelScope.launch {
            repository.getMovieVideo(movieId)?.let { movieVideo ->
                _eventMovieVideo.postValue(Event(movieVideo))
                Log.d("Movie video", "openMovieTrailer:  ${movieVideo.movieUrl+movieVideo.key}")
            } ?: Log.d("Movie video","Trailer cannot be play")
        }
    }

}