package com.example.nagwatask.domain.usecases

import com.example.nagwatask.data.model.MovieResponse
import com.example.nagwatask.domain.repos.MovieRepository

class MovieUseCase(private val movieRepository: MovieRepository) {

    fun getMoviesList(): MutableList<MovieResponse> {

        try {
            val response = movieRepository.getList()
            val list: MutableList<MovieResponse> = mutableListOf()

            response.forEach {
                list.add(
                    MovieResponse(
                        id = it.id,
                        type = it.type,
                        name = it.name,
                        url = it.url
                    )
                )
            }


            return list
        } catch (e: Exception) {
            return mutableListOf()
        }
    }

}