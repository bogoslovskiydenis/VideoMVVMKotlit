package com.example.videomvvmkotlit.repository

import androidx.lifecycle.LiveData
import com.example.videomvvmkotlit.api.MovieDbInterface
import com.example.videomvvmkotlit.network.MovieDetailsNetworkSource
import com.example.videomvvmkotlit.network.NetworkState
import com.example.videomvvmkotlit.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable


//Конструкток с интерфейсом БД
class MovieDetailsRepository (
    private val apiSource: MovieDbInterface
        ){
    lateinit var movieDetailsNetworkSource: MovieDetailsNetworkSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int):LiveData<MovieDetails>{
        movieDetailsNetworkSource = MovieDetailsNetworkSource(apiSource,compositeDisposable)
        movieDetailsNetworkSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkSource.downloadedMovieDetailsResponse
    }

    fun getMovieDetailsNetworkSource (): LiveData<NetworkState>{
        return movieDetailsNetworkSource.networkState
    }
}