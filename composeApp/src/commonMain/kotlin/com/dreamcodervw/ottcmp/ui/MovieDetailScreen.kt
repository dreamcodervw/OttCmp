package com.dreamcodervw.ottcmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.dreamcodervw.ottcmp.domain.MovieDetails
import com.dreamcodervw.ottcmp.presentation.MovieDetailsViewModel
import com.dreamcodervw.ottcmp.presentation.UiState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    when (val s = state) {
        is UiState.Loading -> LoadingView(modifier)
        is UiState.Error ->
            ErrorView(
                message = s.message,
                onRetry = { viewModel.load() },
                modifier = modifier,
            )
        is UiState.Content -> MovieDetailContent(details = s.data, onBack = onBack, modifier = modifier)
    }
}

@Composable
private fun MovieDetailContent(
    details: MovieDetails,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }
        Spacer(modifier = Modifier.height(12.dp))

        if (details.posterUrl != null) {
            KamelImage(
                resource = asyncPainterResource(details.posterUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 220.dp, height = 330.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(text = details.title, style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "Rating: ${details.voteAverage}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        details.releaseDate?.let {
            Text(
                text = "Release: $it",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Text(text = details.overview, style = MaterialTheme.typography.bodyMedium)
    }
}

