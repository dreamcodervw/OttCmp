package com.dreamcodervw.ottcmp.tmdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbPagedResponse<T>(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<T>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
)

@Serializable
data class MovieDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("vote_average") val voteAverage: Double = 0.0,
)

@Serializable
data class SeriesDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("vote_average") val voteAverage: Double = 0.0,
)

@Serializable
data class MovieDetailDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("vote_average") val voteAverage: Double = 0.0,
    @SerialName("release_date") val releaseDate: String? = null,
)

@Serializable
data class SeriesDetailDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("vote_average") val voteAverage: Double = 0.0,
    @SerialName("first_air_date") val firstAirDate: String? = null,
)

