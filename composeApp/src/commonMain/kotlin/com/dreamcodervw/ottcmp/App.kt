package com.dreamcodervw.ottcmp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dreamcodervw.ottcmp.di.rememberKoinInject
import com.dreamcodervw.ottcmp.presentation.MovieDetailsViewModel
import com.dreamcodervw.ottcmp.presentation.MoviesViewModel
import com.dreamcodervw.ottcmp.presentation.SeriesDetailsViewModel
import com.dreamcodervw.ottcmp.presentation.SeriesViewModel
import com.dreamcodervw.ottcmp.ui.MovieDetailScreen
import com.dreamcodervw.ottcmp.ui.MoviesTabScreen
import com.dreamcodervw.ottcmp.ui.Screen
import com.dreamcodervw.ottcmp.ui.SeriesDetailScreen
import com.dreamcodervw.ottcmp.ui.SeriesTabScreen
import org.koin.compose.KoinContext
import org.koin.core.parameter.parametersOf

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            var screen: Screen by remember { mutableStateOf(Screen.Home) }
            var selectedTab by remember { mutableStateOf(0) }

            Scaffold { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    when (val s = screen) {
                        is Screen.Home -> {
                            val moviesViewModel: MoviesViewModel = rememberKoinInject()
                            val seriesViewModel: SeriesViewModel = rememberKoinInject()

                            PrimaryTabRow(selectedTabIndex = selectedTab) {
                                Tab(
                                    selected = selectedTab == 0,
                                    onClick = { selectedTab = 0 },
                                    text = { Text("Movies") },
                                )
                                Tab(
                                    selected = selectedTab == 1,
                                    onClick = { selectedTab = 1 },
                                    text = { Text("Series") },
                                )
                            }

                            when (selectedTab) {
                                0 -> MoviesTabScreen(
                                    viewModel = moviesViewModel,
                                    onMovieClick = { screen = Screen.MovieDetail(it.id) },
                                    modifier = Modifier.fillMaxSize(),
                                )

                                else -> SeriesTabScreen(
                                    viewModel = seriesViewModel,
                                    onSeriesClick = { screen = Screen.SeriesDetail(it.id) },
                                    modifier = Modifier.fillMaxSize(),
                                )
                            }
                        }

                        is Screen.MovieDetail -> {
                            val viewModel: MovieDetailsViewModel =
                                rememberKoinInject(key = s.id) { parametersOf(s.id) }
                            MovieDetailScreen(
                                viewModel = viewModel,
                                onBack = { screen = Screen.Home },
                            )
                        }

                        is Screen.SeriesDetail -> {
                            val viewModel: SeriesDetailsViewModel =
                                rememberKoinInject(key = s.id) { parametersOf(s.id) }
                            SeriesDetailScreen(
                                viewModel = viewModel,
                                onBack = { screen = Screen.Home },
                            )
                        }
                    }
                }
            }
        }
    }
}
