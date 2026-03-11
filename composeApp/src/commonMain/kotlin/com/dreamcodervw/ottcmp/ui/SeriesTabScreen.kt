package com.dreamcodervw.ottcmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.dreamcodervw.ottcmp.domain.Series
import com.dreamcodervw.ottcmp.presentation.SeriesViewModel
import com.dreamcodervw.ottcmp.presentation.UiState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun SeriesTabScreen(
    viewModel: SeriesViewModel,
    onSeriesClick: (Series) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPopular()
    }

    when (val s = state) {
        is UiState.Loading -> LoadingView(modifier)
        is UiState.Error ->
            ErrorView(
                message = s.message,
                onRetry = { viewModel.loadPopular() },
                modifier = modifier,
            )
        is UiState.Content -> {
            LazyColumn(modifier = modifier) {
                items(s.data, key = { it.id }) { item ->
                    SeriesRow(item = item, onClick = { onSeriesClick(item) })
                }
            }
        }
    }
}

@Composable
private fun SeriesRow(
    item: Series,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            PosterThumbnail(
                url = item.posterUrl,
                modifier = Modifier
                    .size(width = 80.dp, height = 120.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Rating: ${item.voteAverage}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (item.overview.isNotBlank()) {
                    Text(
                        text = item.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                    )
                }
            }
        }
    }
}

@Composable
private fun PosterThumbnail(
    url: String?,
    modifier: Modifier = Modifier,
) {
    if (url == null) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surfaceVariant),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {}
        return
    }

    KamelImage(
        resource = asyncPainterResource(url),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

