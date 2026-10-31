package fr.danersharifi.marslens.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.danersharifi.marslens.R
import fr.danersharifi.marslens.data.model.MarsPhoto
import fr.danersharifi.marslens.ui.components.PhotoCard

@Composable
fun PhotosScreen(
    uiState: PhotosUiState,
    onRetry: () -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when {
        uiState.isLoading -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(all = 24.dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()

                Text(
                    text = stringResource(id = R.string.photos_loading),
                )
            }
        }

        uiState.hasError -> {
            PhotosMessage(
                title = stringResource(id = R.string.photos_error_title),
                message = stringResource(id = R.string.photos_error_message),
                onRetry = onRetry,
                modifier = modifier,
            )
        }

        uiState.photos.isEmpty() -> {
            PhotosMessage(
                title = stringResource(id = R.string.photos_empty_title),
                message = stringResource(id = R.string.photos_empty_message),
                modifier = modifier,
            )
        }

        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            ) {
                item {
                    Text(
                        text = stringResource(
                            id = R.string.photos_count,
                            formatArgs = arrayOf(uiState.total),
                        ),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }

                items(
                    items = uiState.photos,
                    key = { photo -> photo.id },
                ) { photo ->
                    PhotoCard(
                        photo = photo,
                    )
                }

                if (uiState.hasMore) {
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(
                                space = 8.dp,
                            ),
                        ) {
                            if (uiState.hasLoadMoreError) {
                                Text(
                                    text = stringResource(
                                        id = R.string.photos_load_more_error,
                                    ),
                                    color = MaterialTheme.colorScheme.error,
                                )
                            }

                            Button(
                                onClick = onLoadMore,
                                enabled = !uiState.isLoadingMore,
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                if (uiState.isLoadingMore) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(size = 20.dp),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        strokeWidth = 2.dp,
                                    )
                                } else {
                                    Text(
                                        text = stringResource(
                                            id = R.string.photos_load_more,
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Shared layout for empty res and req errors
@Composable
private fun PhotosMessage(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )

        Text(
            text = message,
            textAlign = TextAlign.Center,
        )

        if (onRetry != null) {
            Button(
                onClick = onRetry,
            ) {
                Text(
                    text = stringResource(id = R.string.photos_retry),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotosScreenPreview() {
    MaterialTheme {
        PhotosScreen(
            uiState = PhotosUiState(
                photos = listOf(
                    MarsPhoto(
                        id = 259238,
                        sol = 1000,
                        mission = "msl",
                        title = "Sol 1000: Chemistry & Camera",
                        camera = "CHEMCAM_RMI",
                        imageUrl = "",
                        dateTaken = null,
                        description = null,
                        imageCredit = null,
                    ),
                ),
                isLoading = false,
                total = 1,
            ),
            onRetry = {},
            onLoadMore = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotosLoadingPreview() {
    MaterialTheme {
        PhotosScreen(
            uiState = PhotosUiState(),
            onRetry = {},
            onLoadMore = {},
        )
    }
}
