package com.lianda.movies.presentation.main

import androidx.recyclerview.widget.GridLayoutManager
import com.lianda.movies.R
import com.lianda.movies.base.BaseActivity
import com.lianda.movies.domain.model.Movie
import com.lianda.movies.presentation.adapter.MovieAdapter
import com.lianda.movies.presentation.movie.MovieDetailActivity
import com.lianda.movies.presentation.viewmodel.MovieViewModel
import com.lianda.movies.utils.common.ResultState
import com.lianda.movies.utils.extentions.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val movieViewModel: MovieViewModel by viewModel()

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(this, listOf()) { movie ->
            MovieDetailActivity.start(this, movie)
        }
    }

    override val layout: Int = R.layout.activity_main

    override fun onPreparation() {
    }

    override fun onIntent() {

    }

    override fun onUi() {
        setupMovies()
    }

    override fun onAction() {

    }

    override fun onObserver() {
        observeMovies()
    }

    private fun setupMovies() {
        rvMovies.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = movieAdapter
        }
    }

    private fun observeMovies() {
        observe(
            liveData = movieViewModel.fetchMovies(),
            action = ::manageStateMovie
        )
    }

    private fun manageStateMovie(result: ResultState<List<Movie>>) {
        when (result) {
            is ResultState.Success -> {
                msvMovie.showContentView()
                movieAdapter.notifyDataAddOrUpdate(result.data)
            }
            is ResultState.Error -> {
                msvMovie.showErrorView(
                    icon = R.drawable.ic_movie_broken,
                    title = getString(R.string.label_oops),
                    message = result.throwable.message,
                    action = getString(R.string.action_retry),
                    actionListener = {
                        observeMovies()
                    }
                )
            }
            is ResultState.Loading -> {
                msvMovie.showLoadingView()
            }
            is ResultState.Empty ->{
                msvMovie.showEmptyView(
                    icon = R.drawable.ic_empty,
                    title = getString(R.string.label_oops),
                    message = getString(R.string.message_empty_movies)
                )
            }
        }
    }

}