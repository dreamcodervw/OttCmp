package com.dreamcodervw.ottcmp.presentation

import com.dreamcodervw.ottcmp.data.SeriesRepository
import com.dreamcodervw.ottcmp.domain.Series
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val repo: SeriesRepository,
) : BaseViewModel() {

    private val _state = MutableStateFlow<UiState<List<Series>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Series>>> = _state

    fun loadPopular(page: Int = 1) {
        _state.value = UiState.Loading
        scope.launch {
            runCatching { repo.getPopularSeries(page = page) }
                .onSuccess { _state.value = UiState.Content(it) }
                .onFailure {
                    _state.value = UiState.Error(
                        message = it.message ?: "Failed to load series",
                        cause = it,
                    )
                }
        }
    }
}

