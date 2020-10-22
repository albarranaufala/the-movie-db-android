package com.example.screenplay.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.screenplay.R
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.ui.main.view.MovieDetailActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(items: ArrayList<MovieEntity>) {
        listMovies.clear()
        listMovies.addAll(items)
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                tvTitle.text = movie.title
                tvSynopsis.text = movie.synopsis
                tvRating.text = movie.voteRating.toString()
                Glide.with(context)
                    .load(movie.posterPath)
                    .apply(
                        RequestOptions()
                            .placeholder(R.color.colorAccent)
                            .override(160,240)
                            .fitCenter()
                    )
                    .into(imgPoster)
                setOnClickListener {
                    val intent = Intent(context, MovieDetailActivity::class.java).apply {
                        putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movie.id)
                        putExtra(MovieDetailActivity.EXTRA_MOVIE_TITLE, movie.title)
                        putExtra(MovieDetailActivity.EXTRA_MOVIE_POSTER, movie.posterPath)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size
}