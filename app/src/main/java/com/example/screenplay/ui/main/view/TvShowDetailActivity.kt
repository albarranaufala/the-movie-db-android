package com.example.screenplay.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.screenplay.R
import com.example.screenplay.data.entity.TvShowEntity
import com.example.screenplay.ui.base.ViewModelFactory
import com.example.screenplay.ui.main.viewmodel.TvShowDetailViewModel
import kotlinx.android.synthetic.main.activity_tvshow_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tv_show_id"
        const val EXTRA_TV_SHOW_TITLE = "extra_tv_show_title"
        const val EXTRA_TV_SHOW_POSTER = "extra_tv_show_poster"
    }

    private var tvShowId: Int = 0
    private var tvShowTitle: String? = null
    private var tvShowPoster: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)

        tvShowId = intent.getIntExtra(EXTRA_TV_SHOW_ID, 0)
        tvShowTitle = intent.getStringExtra(EXTRA_TV_SHOW_TITLE)
        tvShowPoster = intent.getStringExtra(EXTRA_TV_SHOW_POSTER)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]
        showProgressBar(true)
        viewModel.getTvShowDetail(tvShowId).observe(this, {
            it?.let { result ->
                showProgressBar(false)
                showTvShow(result)
            }
        })

        Glide.with(this)
                .load(tvShowPoster)
                .apply(
                        RequestOptions()
                                .placeholder(R.color.colorAccent)
                                .override(160,240)
                                .fitCenter()
                )
                .into(imgPosterTvshow)

        if(supportActionBar != null){
            (supportActionBar as ActionBar).title = tvShowTitle
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showTvShow(tvShow: TvShowEntity?){
        if (tvShow != null) {
            tvTitleTvshow.text = tvShow.title
            tvReleaseDate.text = tvShow.releaseDate
            tvSeason.text = "${tvShow.nSeasons} Seasons"
            tvSynopsisTvshow.text = tvShow.synopsis
            tvGenreTvshow.text = tvShow.genres?.joinToString { it.name.toString() }.toString().ifBlank { "-" }
            tvProductionCompany.text = tvShow.productionCompanies?.joinToString { it.name.toString() }.toString().ifBlank { "-" }
            tvRating.text = tvShow.voteRating.toString()
        }
    }

    private fun showProgressBar(isLoading: Boolean){
        if(isLoading){
            progressBar.visibility = View.VISIBLE
            tvSynopsisLabel.visibility = View.GONE
            tvGenreLabel.visibility = View.GONE
            tvProductionCompanyLabel.visibility = View.GONE
            imgRating.visibility = View.GONE
        }
        else{
            progressBar.visibility = View.GONE
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
                val message = "Hello! I recommend you to watch a tv show called ${tvShowTitle}."
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