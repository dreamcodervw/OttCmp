package com.dreamcodervw.ottcmp.presentation

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Content<T>(val data: T) : UiState<T>
    data class Error(val message: String, val cause: Throwable? = null) : UiState<Nothing>
}

