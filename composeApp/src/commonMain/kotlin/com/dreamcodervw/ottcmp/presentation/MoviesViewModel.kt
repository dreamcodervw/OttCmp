package com.dreamcodervw.ottcmp.presentation

import com.dreamcodervw.ottcmp.data.MoviesRepository
import com.dreamcodervw.ottcmp.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repo: MoviesRepository,
) : BaseViewModel() {

    private val _state = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Movie>>> = _state

    fun loadPopular(page: Int = 1) {
        _state.value = UiState.Loading
        scope.launch {
            runCatching { repo.getPopularMovies(page = page) }
                .onSuccess { _state.value = UiState.Content(it) }
                .onFailure {
                    _state.value = UiState.Error(
                        message = it.message ?: "Failed to load movies",
                        cause = it,
                    )
                }
        }
    }
}

