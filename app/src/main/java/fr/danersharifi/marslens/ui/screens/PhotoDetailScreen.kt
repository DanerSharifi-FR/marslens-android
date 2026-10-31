package fr.danersharifi.marslens.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.danersharifi.marslens.R
import fr.danersharifi.marslens.ui.components.PhotoDetailContent
import fr.danersharifi.marslens.ui.components.ScreenMessage

@Composable
fun PhotoDetailScreen(
    uiState: PhotoDetailUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val photo = uiState.photo

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
                    text = stringResource(
                        id = R.string.photo_detail_loading,
                    ),
                )
            }
        }

        uiState.hasError -> {
            ScreenMessage(
                title = stringResource(
                    id = R.string.photo_detail_error,
                ),
                message = stringResource(
                    id = R.string.photos_error_message,
                ),
                onRetry = onRetry,
                modifier = modifier,
            )
        }

        // A successful request can still return no matching photo
        photo == null -> {
            ScreenMessage(
                title = stringResource(
                    id = R.string.photo_detail_not_found,
                ),
                message = stringResource(
                    id = R.string.photo_detail_not_found_message,
                ),
                modifier = modifier,
            )
        }

        else -> {
            PhotoDetailContent(
                photo = photo,
                modifier = modifier.fillMaxSize(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoDetailLoadingPreview() {
    MaterialTheme {
        PhotoDetailScreen(
            uiState = PhotoDetailUiState(),
            onRetry = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoDetailErrorPreview() {
    MaterialTheme {
        PhotoDetailScreen(
            uiState = PhotoDetailUiState(
                isLoading = false,
                hasError = true,
            ),
            onRetry = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoDetailMissingPreview() {
    MaterialTheme {
        PhotoDetailScreen(
            uiState = PhotoDetailUiState(
                isLoading = false,
            ),
            onRetry = {},
        )
    }
}
