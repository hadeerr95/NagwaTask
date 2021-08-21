package com.example.nagwatask.persentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwatask.data.model.MovieResponse
import com.example.nagwatask.databinding.ListItemViewBinding

class MoviesAdapter :
    ListAdapter<MovieResponse, MoviesAdapter.MoviesViewHolder>(MovieDiffCallback()) {


    class MovieDiffCallback : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemViewBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)

        holder.bind(movie)
    }

    override fun onBindViewHolder(
        holder: MoviesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.firstOrNull() != null) {
            with(holder.itemView) {
                (payloads.first() as Bundle).getInt("progress").also {
                    val isVisible = it < 99
//                    imgDownload.progress = it
//                    pb_download.isVisible= isVisible
//                    img_download.isVisible =isVisible
//                    animationView.isVisible = it == 100
//                    tv_percentage.isVisible = it < 99
//                    tv_percentage.text = "$it %"
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MoviesViewHolder
    constructor(

        private val binding: ListItemViewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResponse) = with(itemView) {
            binding.tvIdValue.text = movie.id.toString()
            binding.tvNameValue.text = movie.name.toString()
            binding.tvTypeValue.text = movie.type.toString()
            binding.imgDownload.isVisible = !movie.isFileExist
            binding.animationView.isVisible = movie.isFileExist
            binding.imgDownload.setOnClickListener {
//                interaction?.onItemSelected(movie, binding.pbDownload)

            }
        }

    }

    fun setDownloading(movie: MovieResponse, isDownloading: Boolean, ismovieExist: Boolean) {
        movie.isloading = isDownloading
        movie.isFileExist = ismovieExist
        notifyItemChanged(this.currentList.indexOf(movie))
    }

    fun setProgress(movie: MovieResponse, progress: Int) {
        movie.progress = progress
        notifyItemChanged(
            this.currentList.indexOf(movie),
            Bundle().apply { putInt("progress", progress) })
    }

    interface Interaction {
        fun onItemSelected(movie: MovieResponse, view: ProgressBar)
    }
}