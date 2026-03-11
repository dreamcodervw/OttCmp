package com.dreamcodervw.ottcmp.di

import com.dreamcodervw.ottcmp.data.MoviesRepository
import com.dreamcodervw.ottcmp.data.SeriesRepository
import com.dreamcodervw.ottcmp.network.createHttpClient
import com.dreamcodervw.ottcmp.presentation.MovieDetailsViewModel
import com.dreamcodervw.ottcmp.presentation.MoviesViewModel
import com.dreamcodervw.ottcmp.presentation.SeriesDetailsViewModel
import com.dreamcodervw.ottcmp.presentation.SeriesViewModel
import com.dreamcodervw.ottcmp.tmdb.TmdbApiService
import com.dreamcodervw.ottcmp.tmdb.TmdbApiServiceImpl
import io.ktor.client.HttpClient
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val networkModule = module {
    single<HttpClient> { createHttpClient() }
    single<TmdbApiService> { TmdbApiServiceImpl(client = get()) }
}

private val repositoryModule = module {
    single { MoviesRepository(api = get()) }
    single { SeriesRepository(api = get()) }
}

private val presentationModule = module {
    factory { MoviesViewModel(repo = get()) }
    factory { SeriesViewModel(repo = get()) }
    factory { (movieId: Int) -> MovieDetailsViewModel(movieId = movieId, repo = get()) }
    factory { (seriesId: Int) -> SeriesDetailsViewModel(seriesId = seriesId, repo = get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return org.koin.core.context.startKoin {
        appDeclaration()
        modules(
            networkModule,
            repositoryModule,
            presentationModule,
        )
    }
}
