package com.lianda.movies.presentation.main

import androidx.recyclerview.widget.GridLayoutManager
import com.lianda.movies.R
import com.lianda.movies.base.BaseActivity
import com.lianda.movies.base.BaseEndlessRecyclerViewAdapter
import com.lianda.movies.domain.model.EndlessMovie
import com.lianda.movies.presentation.adapter.MovieAdapter
import com.lianda.movies.presentation.movie.MovieDetailActivity
import com.lianda.movies.presentation.viewmodel.MovieViewModel
import com.lianda.movies.utils.common.ResultState
import com.lianda.movies.utils.extentions.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), BaseEndlessRecyclerViewAdapter.OnLoadMoreListener {

    private val movieViewModel: MovieViewModel by viewModel()

    private var movieAdapter: MovieAdapter? = null

    private var isLoadMore = false

    private var currentPage = 1
    private var totalPages = 0

    override val layout: Int = R.layout.activity_main

    override fun onPreparation() {
        if (movieAdapter == null){
            val gridLayoutManager = GridLayoutManager(this@MainActivity, 2)
            movieAdapter = MovieAdapter(this, mutableListOf()){movie->
                MovieDetailActivity.start(this,movie)
            }
            movieAdapter?.apply{
                page = currentPage
                totalPage = totalPages
                layoutManager = gridLayoutManager
                onLoadMoreListener = this@MainActivity
                recyclerView = rvMovies
            }

            rvMovies.apply {
                layoutManager = gridLayoutManager
                adapter = movieAdapter
            }
        }

    }

    override fun onIntent() {

    }

    override fun onUi() {
    }

    override fun onAction() {

    }

    override fun onObserver() {
        observeMovies()
    }

    private fun observeMovies() {
        observe(
            liveData = movieViewModel.fetchMovies(currentPage),
            action = ::manageStateMovie
        )
    }

    private fun manageStateMovie(result: ResultState<EndlessMovie>) {
        when (result) {
            is ResultState.Success -> {
                msvMovie.showContentView()
                isLoadMore = false
                movieAdapter?.setLoadMoreProgress(false)
                totalPages = result.data.totalPage
                movieAdapter?.totalPage = totalPages
                movieAdapter?.notifyAddOrUpdateChanged(result.data.movies)
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
            is ResultState.Empty -> {
                if (isLoadMore) {
                    isLoadMore = false
                    movieAdapter?.setLoadMoreProgress(false)
                    movieAdapter?.removeScrollListener()
                } else {
                    movieAdapter?.datas?.clear()
                    msvMovie.showEmptyView(
                        icon = R.drawable.ic_empty,
                        title = getString(R.string.label_oops),
                        message = getString(R.string.message_empty_movies)
                    )
                }

            }
        }
    }

    override fun onLoadMore() {
        isLoadMore = true
        movieAdapter?.setLoadMoreProgress(true)
        currentPage += 1
        movieAdapter?.page = currentPage
        observeMovies()
    }

}