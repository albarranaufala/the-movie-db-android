package com.example.screenplay.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screenplay.R
import com.example.screenplay.ui.base.ViewModelFactory
import com.example.screenplay.ui.main.adapter.TvShowAdapter
import com.example.screenplay.ui.main.viewmodel.TvShowViewModel
import com.example.screenplay.utils.Status
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
        viewModel.getTvShows().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        rvTvshows.visibility = View.VISIBLE
                        resource.data?.let { tvShows -> adapter.setTvshows(tvShows) }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.VISIBLE
                        rvTvshows.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        rvTvshows.visibility = View.GONE
                    }
                }
            }
        })
    }
}