package fr.danersharifi.marslens.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import fr.danersharifi.marslens.R

@Composable
fun PhotoImage(
    imageUrl: String,
    description: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Box(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surfaceVariant,
        ),
        contentAlignment = Alignment.Center,
    ) {
        // Previews have no network, so show the description instead
        if (LocalInspectionMode.current) {
            Text(text = description)
        } else {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = description,
                modifier = Modifier.fillMaxSize(),
                contentScale = contentScale,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.photo_image_unavailable,
                            ),
                        )
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoImagePreview() {
    MaterialTheme {
        PhotoImage(
            imageUrl = "",
            description = "Mars photograph",
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp),
        )
    }
}
