package com.example.nagwatask.data.remote

import com.example.nagwatask.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MoviesService {
    @GET("movies")
    fun getAllMovies(): Call<List<MovieResponse>>
}