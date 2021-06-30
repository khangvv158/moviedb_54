package com.sun.moviedb_54.data.source

import com.sun.moviedb_54.data.model.*
import com.sun.moviedb_54.ultis.HotMovieType
import retrofit2.Response

interface MovieDataSource {

    interface Remote {

        suspend fun getHotMovie(typeHotMovie: HotMovieType, page: Int): Response<MovieResponse>

        suspend fun getGenres(): Response<GenresResponse>

        suspend fun getGenresMovie(page: Int, genres: String): Response<GenresMovieResponse>

        suspend fun searchMovie(page: Int, query: String): Response<MovieResponse>

        suspend fun getDetailMovie(idMovie: Int): Response<DetailMovie>

        suspend fun getActor(idMovie: Int): Response<ActorResponse>

        suspend fun getRecommendMovie(idMovie: Int): Response<MovieResponse>

        suspend fun getTrailer(idMovie: Int): Response<VideoResponse>

        suspend fun getActorDetail(idActor: Int): Response<ActorDetail>

        suspend fun getMovieByActor(idActor: Int): Response<MovieResponse>
    }

    interface Local {
        suspend fun getMovieFavorite(): MutableList<MovieFavorite>?

        suspend fun checkFavorite(id: Int): MovieFavorite?

        suspend fun addMovieFavorite(movieFavorite: MovieFavorite)

        suspend fun deleteMovieFavorite(movieFavorite: MovieFavorite)
    }
}
