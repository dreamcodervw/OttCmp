package com.dreamcodervw.ottcmp.presentation

import com.dreamcodervw.ottcmp.data.MoviesRepository
import com.dreamcodervw.ottcmp.domain.MovieDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieId: Int,
    private val repo: MoviesRepository,
) : BaseViewModel() {

    private val _state = MutableStateFlow<UiState<MovieDetails>>(UiState.Loading)
    val state: StateFlow<UiState<MovieDetails>> = _state

    fun load() {
        _state.value = UiState.Loading
        scope.launch {
            runCatching { repo.getMovieDetails(id = movieId) }
                .onSuccess { _state.value = UiState.Content(it) }
                .onFailure {
                    _state.value = UiState.Error(
                        message = it.message ?: "Failed to load movie details",
                        cause = it,
                    )
                }
        }
    }
}

