package com.example.screenplay.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.screenplay.R
import com.example.screenplay.data.MovieEntity
import com.example.screenplay.ui.base.ViewModelFactory
import com.example.screenplay.ui.main.viewmodel.MovieDetailViewModel
import com.example.screenplay.utils.Status
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        const val EXTRA_MOVIE_TITLE = "extra_movie_title"
        const val EXTRA_MOVIE_POSTER = "extra_movie_poster"
    }

    private var movieId: Int = 0
    private var movieTitle: String? = null
    private var moviePoster: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE)
        moviePoster = intent.getStringExtra(EXTRA_MOVIE_POSTER)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]
        viewModel.getMovieDetail(movieId).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showProgressBar(false)
                        resource.data?.let { movie -> showMovie(movie) }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        showProgressBar(true)
                    }
                }
            }
        })

        Glide.with(this)
            .load(moviePoster)
            .apply(
                RequestOptions()
                    .placeholder(R.color.colorAccent)
                    .override(160,240)
                    .fitCenter()
            )
            .into(imgPoster)

        if(supportActionBar != null){
            (supportActionBar as ActionBar).title = movieTitle
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showMovie(movie: MovieEntity?){
        if (movie != null) {
            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvSynopsis.text = movie.synopsis
            tvGenre.text = movie.genres?.joinToString { it.name.toString() }
            tvProductionCompany.text = movie.productionCompanies?.joinToString { it.name.toString() }
            tvRating.text = movie.voteRating.toString()
        }
    }

    private fun showProgressBar(isLoading: Boolean){
        if(isLoading){
            progressBarMovieDetail.visibility = View.VISIBLE
            tvSynopsisLabel.visibility = View.GONE
            tvGenreLabel.visibility = View.GONE
            tvProductionCompanyLabel.visibility = View.GONE
            imgRating.visibility = View.GONE
        }
        else{
            progressBarMovieDetail.visibility = View.GONE
            tvSynopsisLabel.visibility = View.VISIBLE
            tvGenreLabel.visibility = View.VISIBLE
            tvProductionCompanyLabel.visibility = View.VISIBLE
            imgRating.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuShare -> {
                val message = "Hello! I recommend you to watch a movie called ${movieTitle}."
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, message)
                intent.type = "text/plain"

                startActivity(Intent.createChooser(intent, "Share to:"))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}