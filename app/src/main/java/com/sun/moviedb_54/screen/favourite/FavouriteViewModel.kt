package com.sun.moviedb_54.screen.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.moviedb_54.data.model.MovieFavorite
import com.sun.moviedb_54.data.source.repository.MovieRepository
import com.sun.moviedb_54.extensions.plusAssign
import kotlinx.coroutines.launch

class FavouriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _favoriteMovie = MutableLiveData<MutableList<MovieFavorite>>()
    val favoriteMovie: LiveData<MutableList<MovieFavorite>>
        get() = _favoriteMovie

    fun getAllFavoriteMovie(){
        try {
            viewModelScope.launch {
                try {
                    launch {
                        movieRepository.getMovieFavorite()?.also {
                            _favoriteMovie.plusAssign(it)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
