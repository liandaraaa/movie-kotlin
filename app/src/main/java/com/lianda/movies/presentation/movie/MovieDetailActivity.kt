package com.lianda.movies.presentation.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
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

    private val videoPlayer: SimpleExoPlayer by lazy { SimpleExoPlayer.Builder(this).build() }

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
            ratMovie.rating = voteAverage.toFloat()
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
        lifecycle.addObserver(pvTrailer)

        pvTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(data.yotubeKey, 0f)
            }
        })
    }

    private fun showReviews(data: List<Review>) {
        reviewAdapter.notifyDataAddOrUpdate(data)
        rvReview.apply {
            layoutManager = LinearLayoutManager(this@MovieDetailActivity)
            adapter = reviewAdapter
        }
    }

    private fun buildMediaSource(data: Video): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(this, "movie")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(data.url))
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
                msvMovieDetail.showErrorView(
                    icon = R.drawable.ic_movie_broken,
                    title = getString(R.string.label_oops),
                    message = result.throwable.message,
                    action = getString(R.string.action_retry),
                    actionListener = {
                        observeMovieDetail()
                    }
                )
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
                msvTrailer.showErrorView(
                    icon = R.drawable.ic_movie_broken,
                    title = getString(R.string.label_oops),
                    message = result.throwable.message,
                    action = getString(R.string.action_retry),
                    actionListener = {
                        observeVideoTrailer()
                    }
                )
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
                msvReview.showErrorView(
                    icon = R.drawable.ic_movie_broken,
                    title = getString(R.string.label_oops),
                    message = result.throwable.message,
                    action = getString(R.string.action_retry),
                    actionListener = {
                        observeReviews()
                    }
                )
            }
            is ResultState.Loading -> {
                msvReview.showLoadingView()
            }
            is ResultState.Empty -> {
                msvReview.showEmptyView(
                    icon = R.drawable.ic_empty,
                    title = getString(R.string.label_oops),
                    message = getString(R.string.message_empty_reviews)
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        videoPlayer.playWhenReady = false
        if (isFinishing) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        videoPlayer.release()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}