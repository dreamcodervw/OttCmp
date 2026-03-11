package com.dreamcodervw.ottcmp.data

import com.dreamcodervw.ottcmp.domain.Series
import com.dreamcodervw.ottcmp.domain.SeriesDetails
import com.dreamcodervw.ottcmp.domain.toDomain
import com.dreamcodervw.ottcmp.tmdb.TmdbApiService

class SeriesRepository(
    private val api: TmdbApiService,
) {
    suspend fun getPopularSeries(page: Int = 1): List<Series> =
        api.getPopularSeries(page = page).results.map { it.toDomain() }

    suspend fun getSeriesDetails(id: Int): SeriesDetails =
        api.getSeriesDetails(id).toDomain()
}

