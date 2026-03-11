package com.dreamcodervw.ottcmp.ui

sealed interface Screen {
    data object Home : Screen
    data class MovieDetail(val id: Int) : Screen
    data class SeriesDetail(val id: Int) : Screen
}

