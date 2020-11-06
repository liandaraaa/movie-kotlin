package com.lianda.movies.presentation.movie

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.lianda.movies.R
import com.lianda.movies.base.BaseActivity
import com.lianda.movies.domain.model.Movie
import com.lianda.movies.domain.model.Review
import com.lianda.movies.domain.model.Video
import com.lianda.movies.presentation.adapter.ReviewAdapter
import com.lianda.movies.presentation.viewmodel.MovieViewModel
import com.lianda.movies.utils.common.ResultState
import com.lianda.movies.utils.constants.AppConstants.KEY_MOVIE
import com.lianda.movies.utils.extentions.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : BaseActivity() {

    companion object {
        fun start(context: Context, movie: Movie) {
            val starter = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(KEY_MOVIE, movie)
            }
            context.startActivity(starter)
        }
    }

    private val movieViewModel: MovieViewModel by viewModel()

    private val reviewAdapter: ReviewAdapter by lazy { ReviewAdapter(this, listOf()) }

    private var movie: Movie? = null

    override val layout: Int = R.layout.activity_movie_detail

    override fun onPreparation() {
    }

    override fun onIntent() {
        movie = intent.getParcelableExtra(KEY_MOVIE)
    }

    override fun onUi() {
        setupToolbar(toolbar, movie?.title ?: getString(R.string.label_movie), true)
        collapsingToolbar.title = movie?.title ?: getString(R.string.label_movie)
        showMovie()
    }

    override fun onAction() {
    }

    override fun onObserver() {
        observeMovieDetail()
        observeVideoTrailer()
        observeReviews()
    }

    private fun showMovie() {
        movie?.apply {
            imgMovie.loadImage(posterPath, pbPoster)
            tvTitle.text = title
            tvVote.text = voteAverage.toString()
            ratMovie.rating = voteAverage.toFloat().div(2)
            tvDate.text = releaseDate
        }
    }

    private fun showMovieDetail(data: Movie) {
        data.apply {
            tvOriginalLanguage.text = originalLanguage
            tvStatus.text = status
            tvDuration.text = runtime.toReadableMinutes()
            tvGenre.text = genres
            tvDescription.text = overview
        }
    }

    private fun showVideoTrailer(data: Video) {
        if(data.youtubeKey.isNotEmpty()){
            lifecycle.addObserver(pvTrailer)
            pvTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(data.youtubeKey, 0f)
                }

                override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                    super.onError(youTubePlayer, error)
                    onVideoTrailerError()
                }
            })
        }else{
           onVideoTrailerEmpty()
        }
    }

    private fun showReviews(data: List<Review>) {
        reviewAdapter.notifyDataAddOrUpdate(data)
        rvReview.apply {
            layoutManager = LinearLayoutManager(this@MovieDetailActivity)
            adapter = reviewAdapter
        }
    }

    private fun observeMovieDetail() {
        movie?.let { movie ->
            observe(
                liveData = movieViewModel.fetchMovieDetail(movie.id),
                action = ::manageStateMovie
            )
        }
    }

    private fun observeVideoTrailer() {
        movie?.let { movie ->
            observe(
                liveData = movieViewModel.fetchVideoTrailer(movie.id),
                action = ::manageStateVideoTrailer
            )
        }
    }

    private fun observeReviews() {
        movie?.let { movie ->
            observe(
                liveData = movieViewModel.fetchReviews(movie.id),
                action = ::manageStateReviews
            )
        }
    }

    private fun manageStateMovie(result: ResultState<Movie>) {
        when (result) {
            is ResultState.Success -> {
                msvMovieDetail.showContentView()
                showMovieDetail(result.data)
            }
            is ResultState.Error -> {
               onMovieDetailError(result.throwable.message.toString())
            }
            is ResultState.Loading -> {
                msvMovieDetail.showLoadingView()
            }
        }
    }

    private fun manageStateVideoTrailer(result: ResultState<Video>) {
        when (result) {
            is ResultState.Success -> {
                msvTrailer.showContentView()
                showVideoTrailer(result.data)
            }
            is ResultState.Error -> {
                onVideoTrailerError()
            }
            is ResultState.Loading -> {
                msvTrailer.showLoadingView()
            }
        }
    }

    private fun manageStateReviews(result: ResultState<List<Review>>) {
        when (result) {
            is ResultState.Success -> {
                msvReview.showContentView()
                showReviews(result.data)
            }
            is ResultState.Error -> {
               onReviewsError(result.throwable.message.toString())
            }
            is ResultState.Loading -> {
                msvReview.showLoadingView()
            }
            is ResultState.Empty -> {
                onReviewsEmpty()
            }
        }
    }

    private fun onReviewsEmpty() {
        msvReview.showEmptyView(
            icon = R.drawable.ic_empty,
            title = getString(R.string.label_oops),
            message = getString(R.string.message_empty_reviews)
        )
    }

    private fun onReviewsError(message:String) {
        msvReview.showErrorView(
            icon = R.drawable.ic_movie_broken,
            title = getString(R.string.label_oops),
            message = message,
            action = getString(R.string.action_retry),
            actionListener = {
                observeReviews()
            }
        )
    }

    private fun onMovieDetailError(message:String) {
        msvMovieDetail.showErrorView(
            icon = R.drawable.ic_movie_broken,
            title = getString(R.string.label_oops),
            message = message,
            action = getString(R.string.action_retry),
            actionListener = {
                observeMovieDetail()
            }
        )
    }

    private fun onVideoTrailerError(){
        msvTrailer.showErrorView(
            icon = R.drawable.ic_movie_broken,
            title = getString(R.string.label_oops),
            message = getString(R.string.message_error_founded),
            action = getString(R.string.action_retry),
            actionListener = {
                observeVideoTrailer()
            }
        )
    }

    private fun onVideoTrailerEmpty(){
        msvTrailer.showEmptyView(
            icon = R.drawable.ic_empty,
            title = getString(R.string.label_oops),
            message = getString(R.string.message_empty_videos)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}