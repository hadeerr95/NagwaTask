package com.example.nagwatask.persentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.nagwatask.data.model.MovieResponse
import com.example.nagwatask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MoviesAdapter.Interaction {
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    val adapter = MoviesAdapter()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

    }

    private fun setupViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.moviesList.observeForever {
            adapter.submitList(it)
            binding.bookRecycler.adapter = adapter
        }
    }

    override fun onItemSelected(movie: MovieResponse, view: ProgressBar) {
        adapter.setDownloading(movie, isDownloading = false, ismovieExist = true)
    }
}