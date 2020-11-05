package com.lianda.movies.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lianda.movies.R
import com.lianda.movies.base.BaseViewHolder
import com.lianda.movies.domain.model.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(private val context: Context, var data: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_review, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class MovieViewHolder(itemView: View) : BaseViewHolder<Review>(itemView) {
        override fun bind(data: Review) {
            with(itemView) {
                tvName.text = data.author
                tvComment.text = data.content
            }
        }
    }

    fun notifyDataAddOrUpdate(newData: List<Review>) {
        data = newData
        notifyDataSetChanged()
    }
}