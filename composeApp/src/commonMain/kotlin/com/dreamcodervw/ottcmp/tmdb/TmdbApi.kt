package com.dreamcodervw.ottcmp.tmdb

import com.dreamcodervw.ottcmp.network.createHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val TMDB_BASE_URL = "https://api.themoviedb.org/3"
private const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

// TODO: Replace with your own TMDB API key
private const val TMDB_API_KEY: String = "bbd9a0e76bdb636a3a2b78d24674efb6"

fun buildImageUrl(path: String?): String? =
    path?.let { "$TMDB_IMAGE_BASE_URL$it" }

interface TmdbApiService {
    suspend fun getPopularMovies(page: Int = 1): TmdbPagedResponse<MovieDto>
    suspend fun getPopularSeries(page: Int = 1): TmdbPagedResponse<SeriesDto>
    suspend fun getMovieDetails(id: Int): MovieDetailDto
    suspend fun getSeriesDetails(id: Int): SeriesDetailDto
}

class TmdbApiServiceImpl(
    private val client: HttpClient = createHttpClient(),
) : TmdbApiService {

    override suspend fun getPopularMovies(page: Int): TmdbPagedResponse<MovieDto> =
        client.get("$TMDB_BASE_URL/movie/popular") {
            parameter("api_key", TMDB_API_KEY)
            parameter("page", page)
        }.body()

    override suspend fun getPopularSeries(page: Int): TmdbPagedResponse<SeriesDto> =
        client.get("$TMDB_BASE_URL/tv/popular") {
            parameter("api_key", TMDB_API_KEY)
            parameter("page", page)
        }.body()

    override suspend fun getMovieDetails(id: Int): MovieDetailDto =
        client.get("$TMDB_BASE_URL/movie/$id") {
            parameter("api_key", TMDB_API_KEY)
        }.body()

    override suspend fun getSeriesDetails(id: Int): SeriesDetailDto =
        client.get("$TMDB_BASE_URL/tv/$id") {
            parameter("api_key", TMDB_API_KEY)
        }.body()
}

