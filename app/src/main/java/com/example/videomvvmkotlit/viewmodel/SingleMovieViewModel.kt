package com.example.videomvvmkotlit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.videomvvmkotlit.network.NetworkState
import com.example.videomvvmkotlit.repository.MovieDetailsRepository
import com.example.videomvvmkotlit.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
//Очищаем память
class SingleMovieViewModel (
    private val movieRepository: MovieDetailsRepository, movieId : Int):ViewModel(){

        private  val compositeDisposable = CompositeDisposable()
    val  movieDetails : LiveData<MovieDetails> by lazy{
        movieRepository.fetchSingleMovieDetails(compositeDisposable,movieId)
    }
      val  networkState : LiveData<NetworkState> by lazy{
        movieRepository.getMovieDetailsNetworkSource()
    }

    override fun onCleared() {
        super.onCleared()
    compositeDisposable.dispose()
    }
}