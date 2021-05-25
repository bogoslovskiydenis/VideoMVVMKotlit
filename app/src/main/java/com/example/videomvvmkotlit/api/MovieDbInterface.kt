package com.example.videomvvmkotlit.api

import com.example.videomvvmkotlit.vo.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Single

interface MovieDbInterface {
    /**BaseURl -> Retrofit
    https://developers.themoviedb.org/3/
    GetPopular
    https://api.themoviedb.org/3/movie/popular?api_key=cad40441ab95aa93c214e8d6c53636d1
    GetDetails
    https://api.themoviedb.org/3/movie/460465?api_key=cad40441ab95aa93c214e8d6c53636d1 **/

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}