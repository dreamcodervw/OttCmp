package com.dreamcodervw.ottcmp.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String?,
    val voteAverage: Double,
)

data class Series(
    val id: Int,
    val name: String,
    val overview: String,
    val posterUrl: String?,
    val voteAverage: Double,
)

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val voteAverage: Double,
    val releaseDate: String?,
)

data class SeriesDetails(
    val id: Int,
    val name: String,
    val overview: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val voteAverage: Double,
    val firstAirDate: String?,
)

