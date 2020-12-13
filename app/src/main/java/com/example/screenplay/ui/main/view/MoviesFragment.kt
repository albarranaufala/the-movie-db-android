package com.example.screenplay.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screenplay.R
import com.example.screenplay.ui.base.ViewModelFactory
import com.example.screenplay.ui.main.adapter.MovieAdapter
import com.example.screenplay.ui.main.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    private lateinit var adapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onActivityCreated(savedInstanceState)
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()

        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.adapter = adapter

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        viewModel.getMovies().observe(this, { movies ->
            progressBar.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE
            adapter.setMovies(movies)
        })
    }
}