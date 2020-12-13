package com.example.screenplay.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screenplay.R
import com.example.screenplay.ui.base.ViewModelFactory
import com.example.screenplay.ui.main.adapter.TvShowAdapter
import com.example.screenplay.ui.main.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tvshows.*

class TvShowsFragment : Fragment() {
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TvShowAdapter()
        adapter.notifyDataSetChanged()

        rvTvshows.layoutManager = LinearLayoutManager(context)
        rvTvshows.adapter = adapter

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        viewModel.getTvShows().observe(this, { results ->
            progressBar.visibility = View.GONE
            rvTvshows.visibility = View.VISIBLE
            adapter.setTvshows(results)
        })
    }
}