package com.peiyaoxin.flixster

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var orientation = Configuration.ORIENTATION_PORTRAIT

    // Expensive operation: create a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    // Cheap operation: simply bind data to an existing viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    fun setOrientation(orientation:Int) {
        this.orientation = orientation
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide.with(context)
                    .load(movie.posterImageUrl)
                    .placeholder(ColorDrawable(Color.GRAY))
                    .apply(RequestOptions().override(330, 600))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivPoster)
            } else {
                Glide.with(context)
                    .load(movie.backdropImageUrl)
                    .placeholder(ColorDrawable(Color.GRAY))
                    .apply(RequestOptions().override(330, 330))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivPoster)
            }
        }
    }
}
