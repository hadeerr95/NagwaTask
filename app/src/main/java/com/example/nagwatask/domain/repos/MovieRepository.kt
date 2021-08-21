package com.example.nagwatask.domain.repos

import com.example.nagwatask.data.model.MovieResponse
import com.example.nagwatask.data.remote.ApiServiceClient
import com.example.nagwatask.data.remote.MoviesService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepository {

    private val apiServiceClient: MoviesService = ApiServiceClient().client
    var movies: MutableList<MovieResponse> = mutableListOf()


    fun getList(): MutableList<MovieResponse> {
        apiServiceClient.getAllMovies().enqueue(object : Callback<List<MovieResponse>> {
            override fun onResponse(
                call: Call<List<MovieResponse>>,
                response: Response<List<MovieResponse>>
            ) {
                val gson = Gson()
                val movieListType = object : TypeToken<MovieResponse>() {}.type
                if (response.isSuccessful) {
                    movies.addAll(gson.fromJson(response.body()?.toString(), movieListType))
                } else {

                    movies.addAll(gson.fromJson(MovieResponse.jsonList, movieListType))
                }
            }

            override fun onFailure(call: Call<List<MovieResponse>>, t: Throwable) {

            }

        })
        return movies
    }
}