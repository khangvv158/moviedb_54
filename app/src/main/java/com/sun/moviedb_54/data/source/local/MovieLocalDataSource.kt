package com.sun.moviedb_54.data.source.local

import com.sun.moviedb_54.data.model.MovieFavorite
import com.sun.moviedb_54.data.source.MovieDataSource
import com.sun.moviedb_54.data.source.local.room.MovieFavoriteDAO

class MovieLocalDataSource(private val movieFavoriteDAO: MovieFavoriteDAO) : MovieDataSource.Local {

    override suspend fun getMovieFavorite(): MutableList<MovieFavorite>? =
        movieFavoriteDAO.getAllMovie()

    override suspend fun checkFavorite(id: Int): MovieFavorite? =
        movieFavoriteDAO.checkFavorite(id)

    override suspend fun addMovieFavorite(movieFavorite: MovieFavorite) =
        movieFavoriteDAO.addMovie(movieFavorite)

    override suspend fun deleteMovieFavorite(movieFavorite: MovieFavorite) =
        movieFavoriteDAO.deleteMovie(movieFavorite)
}
