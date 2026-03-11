package com.dreamcodervw.ottcmp.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.io.IOException
import kotlinx.serialization.json.Json

fun createHttpClient() = HttpClient {
    // Common JSON configuration
    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = true
            isLenient = true
            coerceInputValues = true
            ignoreUnknownKeys = true
        })
    }

    // Reasonable timeouts to avoid hanging calls
    install(HttpTimeout) {
        requestTimeoutMillis = 15_000
        connectTimeoutMillis = 10_000
        socketTimeoutMillis = 15_000
    }

    // Retry once on transient connection errors (e.g., connection reset)
    install(HttpRequestRetry) {
        maxRetries = 1
        retryOnExceptionIf { _, cause ->
            // Covers connection reset and other IO-related flakiness, especially on desktop
            cause is IOException
        }
        exponentialDelay()
    }
}