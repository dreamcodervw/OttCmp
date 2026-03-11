package com.dreamcodervw.ottcmp.domain

import com.dreamcodervw.ottcmp.tmdb.MovieDetailDto
import com.dreamcodervw.ottcmp.tmdb.MovieDto
import com.dreamcodervw.ottcmp.tmdb.SeriesDetailDto
import com.dreamcodervw.ottcmp.tmdb.SeriesDto
import com.dreamcodervw.ottcmp.tmdb.buildImageUrl

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = buildImageUrl(posterPath),
    voteAverage = voteAverage,
)

fun SeriesDto.toDomain(): Series = Series(
    id = id,
    name = name,
    overview = overview,
    posterUrl = buildImageUrl(posterPath),
    voteAverage = voteAverage,
)

fun MovieDetailDto.toDomain(): MovieDetails = MovieDetails(
    id = id,
    title = title,
    overview = overview,
    posterUrl = buildImageUrl(posterPath),
    backdropUrl = buildImageUrl(backdropPath),
    voteAverage = voteAverage,
    releaseDate = releaseDate,
)

fun SeriesDetailDto.toDomain(): SeriesDetails = SeriesDetails(
    id = id,
    name = name,
    overview = overview,
    posterUrl = buildImageUrl(posterPath),
    backdropUrl = buildImageUrl(backdropPath),
    voteAverage = voteAverage,
    firstAirDate = firstAirDate,
)

