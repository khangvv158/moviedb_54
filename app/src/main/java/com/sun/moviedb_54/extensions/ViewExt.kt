package com.sun.moviedb_54.extensions

import android.widget.ImageView
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadFromUrl(url: String) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .skipMemoryCache(false)
        .into(this)
}

fun RatingBar.setVoteRating(voteAverage: Double?) {
    this.rating = voteAverage?.div(2)?.toFloat() ?: -1f
}
