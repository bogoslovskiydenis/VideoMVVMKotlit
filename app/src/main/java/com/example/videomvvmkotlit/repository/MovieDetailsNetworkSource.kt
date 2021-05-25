package com.example.videomvvmkotlit.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.videomvvmkotlit.api.MovieDbInterface
import com.example.videomvvmkotlit.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MovieDetailsNetworkSource(
    private val apiService: MovieDbInterface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState


    private val _downloadedMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadedMovieDetailsResponse: LiveData<MovieDetails>
        get() = _downloadedMovieDetailsResponse


    fun fetchMovieDetails (movieId: Int){
        _networkState.postValue(NetworkState.LOADED)

        try {
            compositeDisposable.add(apiService.getMovieDetails(movieId).subscribeOn(Schedulers.io())
                .subscribe({
                           _downloadedMovieDetailsResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                },{
                    _networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieDetails", it.message!!)
                })

            )
        }catch (e: Exception){
            Log.e("MovieDetails", e.message!!)

        }
    }
}