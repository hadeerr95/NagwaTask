package com.example.nagwatask.data.remote

import com.example.nagwatask.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceClient {


    val client: MoviesService = getInstance()

    companion object {
        var retrofit: Retrofit? = null
        private fun getInstance(): MoviesService {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit?.create(MoviesService::class.java)!!
        }
    }
}