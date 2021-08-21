package com.example.nagwatask.persentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nagwatask.data.model.MovieResponse
import com.example.nagwatask.domain.usecases.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel(private val mainUseCase: MovieUseCase) : ViewModel() {
    private var _moviesList: MutableLiveData<List<MovieResponse>> = MutableLiveData()
    var moviesList: MutableLiveData<List<MovieResponse>> = _moviesList

    init {
        getMoviesFromRepo()
    }

    private fun getMoviesFromRepo() {
        viewModelScope.launch {
            mainUseCase.getMoviesList().let {
                moviesList.postValue(it)

            }
        }

    }

}