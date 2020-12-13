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
import kotlinx.android.synthetic.main.item_tvshow.view.imgPoster
import kotlinx.android.synthetic.main.item_tvshow.view.tvRating
import kotlinx.android.synthetic.main.item_tvshow.view.tvSynopsis
import kotlinx.android.synthetic.main.item_tvshow.view.tvTitle

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvshowViewHolder>() {

    private var listTvshows = ArrayList<TvShowEntity>()

    fun setTvshows(items: ArrayList<TvShowEntity>) {
        listTvshows.clear()
        listTvshows.addAll(items)
        notifyDataSetChanged()
    }

    class TvshowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvshow: TvShowEntity) {
            with(itemView) {
                tvTitle.text = tvshow.title
                tvSynopsis.text = tvshow.synopsis
                tvRating.text = tvshow.voteRating.toString()
                Glide.with(context)
                        .load(tvshow.posterPath)
                        .apply(
                                RequestOptions()
                                        .placeholder(R.color.colorAccent)
                                        .override(160,240)
                                        .fitCenter()
                        )
                        .into(imgPoster)
                setOnClickListener {
                    val intent = Intent(context, TvShowDetailActivity::class.java).apply {
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_ID, tvshow.id)
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_TITLE, tvshow.title)
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_POSTER, tvshow.posterPath)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow, parent, false)
        return TvshowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvshowViewHolder, position: Int) {
        val movie = listTvshows[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTvshows.size
}