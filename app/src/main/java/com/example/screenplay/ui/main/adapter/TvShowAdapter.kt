package com.example.screenplay.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.screenplay.R
import com.example.screenplay.data.entity.TvShowEntity
import com.example.screenplay.ui.main.view.TvShowDetailActivity
import kotlinx.android.synthetic.main.item_tvshow.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShows = ArrayList<TvShowEntity>()

    fun setTvShows(items: ArrayList<TvShowEntity>) {
        listTvShows.clear()
        listTvShows.addAll(items)
        notifyDataSetChanged()
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowEntity) {
            with(itemView) {
                tvTitle.text = tvShow.title
                tvSynopsis.text = tvShow.synopsis
                tvRating.text = tvShow.voteRating.toString()
                Glide.with(context)
                        .load(tvShow.posterPath)
                        .apply(
                                RequestOptions()
                                        .placeholder(R.color.colorAccent)
                                        .override(160,240)
                                        .fitCenter()
                        )
                        .into(imgPoster)
                setOnClickListener {
                    val intent = Intent(context, TvShowDetailActivity::class.java).apply {
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_ID, tvShow.id)
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_TITLE, tvShow.title)
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_POSTER, tvShow.posterPath)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val movie = listTvShows[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTvShows.size
}