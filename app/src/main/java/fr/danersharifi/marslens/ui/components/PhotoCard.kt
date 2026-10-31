package fr.danersharifi.marslens.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.danersharifi.marslens.R
import fr.danersharifi.marslens.data.model.MarsPhoto

@Composable
fun PhotoCard(
    photo: MarsPhoto,
    modifier: Modifier = Modifier,
) {
    val title = photo.title.ifBlank {
        stringResource(
            id = R.string.photo_title_fallback,
            formatArgs = arrayOf(photo.id),
        )
    }

    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        PhotoImage(
            imageUrl = photo.imageUrl,
            description = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = photo.camera.ifBlank {
                    stringResource(id = R.string.photo_camera_unknown)
                },
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = stringResource(
                    id = R.string.photo_sol,
                    formatArgs = arrayOf(photo.sol),
                ),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoCardPreview() {
    MaterialTheme {
        PhotoCard(
            photo = MarsPhoto(
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
            modifier = Modifier.padding(all = 16.dp),
        )
    }
}
