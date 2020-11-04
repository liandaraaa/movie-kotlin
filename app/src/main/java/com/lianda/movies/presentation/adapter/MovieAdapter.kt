package com.lianda.movies.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lianda.movies.R
import com.lianda.movies.domain.entities.Movie
import com.lianda.movies.utils.extentions.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val context:Context, var data:List<Movie>):
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class MovieViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(data:Movie){
            with(itemView){
                imgPoster.loadImage(data.posterPath,pbPoster)
                tvTitle.text = data.title
                tvDate.text = data.releaseDate
                tvVote.text = data.voteAverage.toString()
            }
        }
    }

    fun notifyDataAddOrUpdate(newData:List<Movie>){
        data = newData
        notifyDataSetChanged()
    }
}