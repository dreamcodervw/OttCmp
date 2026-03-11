package com.dreamcodervw.ottcmp.data

import com.dreamcodervw.ottcmp.domain.Movie
import com.dreamcodervw.ottcmp.domain.MovieDetails
import com.dreamcodervw.ottcmp.domain.toDomain
import com.dreamcodervw.ottcmp.tmdb.TmdbApiService

class MoviesRepository(
    private val api: TmdbApiService,
) {
    suspend fun getPopularMovies(page: Int = 1): List<Movie> =
        api.getPopularMovies(page = page).results.map { it.toDomain() }

    suspend fun getMovieDetails(id: Int): MovieDetails =
        api.getMovieDetails(id).toDomain()
}

