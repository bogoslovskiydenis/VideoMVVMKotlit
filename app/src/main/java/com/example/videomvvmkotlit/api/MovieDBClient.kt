package com.example.videomvvmkotlit.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val API_KEY = "cad40441ab95aa93c214e8d6c53636d1"
const val BASE_URL = "https://developers.themoviedb.org/3/"

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"


object MovieDBClient {
    fun getClient(): MovieDbInterface {
        val requestInterceptor = Interceptor { chain ->
            //take only one argument which is a labmda fun

            val url =
                chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

            val request = chain.request().newBuilder().url(url).build()

            return@Interceptor chain.proceed(request) //returm value from @annotation  , lambda retunrs the value

        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()


        return  Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDbInterface::class.java)
    }
}