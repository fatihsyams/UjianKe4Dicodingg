package com.example.ujianke4dicoding

import com.example.ujianke4dicoding.response.MoviesResponse
import com.example.ujianke4dicoding.response.responsetv.TvResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface Service {

    @GET("discover/movie?api_key=397453e48ab34583881ed2ca68f814df&language=en-US")
    suspend fun getMovie() : MoviesResponse

    @GET("discover/tv?api_key=397453e48ab34583881ed2ca68f814df&language=en-US")
    suspend fun getTv() : TvResponse


    companion object {
        private val BASE_URL = "https://api.themoviedb.org/3/"
        fun create(): Service {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Service::class.java)
        }
    }
}