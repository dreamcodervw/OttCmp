package com.dreamcodervw.ottcmp.presentation

import com.dreamcodervw.ottcmp.data.SeriesRepository
import com.dreamcodervw.ottcmp.domain.SeriesDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SeriesDetailsViewModel(
    private val seriesId: Int,
    private val repo: SeriesRepository,
) : BaseViewModel() {

    private val _state = MutableStateFlow<UiState<SeriesDetails>>(UiState.Loading)
    val state: StateFlow<UiState<SeriesDetails>> = _state

    fun load() {
        _state.value = UiState.Loading
        scope.launch {
            runCatching { repo.getSeriesDetails(id = seriesId) }
                .onSuccess { _state.value = UiState.Content(it) }
                .onFailure {
                    _state.value = UiState.Error(
                        message = it.message ?: "Failed to load series details",
                        cause = it,
                    )
                }
        }
    }
}

