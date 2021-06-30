package com.sun.moviedb_54.screen.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.moviedb_54.data.model.MovieFavorite
import com.sun.moviedb_54.databinding.ItemFavouriteBinding
import com.sun.moviedb_54.ultis.BindingDataRecyclerView
import kotlinx.android.synthetic.main.item_favourite.view.*

class FavouriteAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<MovieFavorite, FavouriteAdapter.FavoriteViewHolder>(FavoriteDiffCallBack()),
    BindingDataRecyclerView<MutableList<MovieFavorite>> {

    override fun setData(data: MutableList<MovieFavorite>?) {
        data?.let {
            submitList(data.toMutableList())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class FavoriteViewHolder(
        private val binding: ItemFavouriteBinding,
        private val onItemClick: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: MovieFavorite) {
            binding.movieFavorite = movie
            binding.root.imageFavoriteMovie.setOnClickListener {
                onItemClick(movie.idMovie)
            }
            binding.executePendingBindings()
        }
    }

    class FavoriteDiffCallBack : DiffUtil.ItemCallback<MovieFavorite>() {
        override fun areItemsTheSame(oldItem: MovieFavorite, newItem: MovieFavorite): Boolean {
            return oldItem.idMovie == newItem.idMovie
        }

        override fun areContentsTheSame(oldItem: MovieFavorite, newItem: MovieFavorite): Boolean {
            return oldItem == newItem
        }
    }
}
