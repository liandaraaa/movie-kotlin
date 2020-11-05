package com.lianda.movies.presentation.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.lianda.movies.R
import com.lianda.movies.base.BaseEndlessRecyclerViewAdapter
import com.lianda.movies.base.BaseViewHolder
import com.lianda.movies.domain.model.Movie
import com.lianda.movies.utils.extentions.loadImage
import com.lianda.movies.utils.extentions.onSingleClickListener
import kotlinx.android.synthetic.main.item_loading.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    override val context: Context,
    override var datas: MutableList<Movie>,
    val onMovieClicked: ((movie: Movie) -> Unit)?
) :
    BaseEndlessRecyclerViewAdapter<Movie>(context, datas) {

    companion object {
        val LOAD_MORE_ITEM = Movie()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        return if (viewType == VIEW_TYPE_ITEM) {
            MovieViewHolder(getView(parent, viewType))
        } else {
            LoadMoreViewHolder(getView(parent, viewType))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (datas[position] != LOAD_MORE_ITEM) {
            VIEW_TYPE_ITEM
        } else {
            VIEW_TYPE_LOAD_MORE
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        holder.bind(data = datas[position])
    }

    override fun getItemResourceLayout(viewType: Int): Int {
        return if (viewType == VIEW_TYPE_ITEM) {
            R.layout.item_movie
        } else {
            R.layout.item_loading
        }
    }

    public override fun setLoadMoreProgress(isProgress: Boolean) {
        isLoadMoreLoading = isProgress
        if (isProgress) {
            datas.add(datas.size, LOAD_MORE_ITEM)
        } else {
            if (datas.size > 0) {
                datas.remove(LOAD_MORE_ITEM)
            }
        }
        notifyDataSetChanged()
    }


    override fun getItemCount() = datas.size

    inner class MovieViewHolder(itemView: View) : BaseViewHolder<Movie>(itemView) {
        override fun bind(data: Movie) {
            with(itemView) {
                imgPoster.loadImage(data.posterPath, pbPoster)
                tvTitle.text = data.title
                tvDate.text = data.releaseDate
                tvVote.text = data.voteAverage.toString()

                onSingleClickListener {
                    onMovieClicked?.invoke(data)
                }
            }
        }
    }


    inner class LoadMoreViewHolder(
        view: View
    ) : BaseViewHolder<Movie>(view) {
        override fun bind(data: Movie) {
            with(itemView) {
                if (isLoadMoreLoading) {
                    pbLoading.visibility = View.VISIBLE
                } else {
                    pbLoading.visibility = View.GONE
                }
            }
        }
    }
}