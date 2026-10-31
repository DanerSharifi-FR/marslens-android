package fr.danersharifi.marslens.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.danersharifi.marslens.R
import fr.danersharifi.marslens.data.model.MarsPhoto
import fr.danersharifi.marslens.data.model.Mission

@Composable
fun PhotoDetailContent(
    photo: MarsPhoto,
    modifier: Modifier = Modifier,
) {
    val title = photo.title.ifBlank {
        stringResource(
            id = R.string.photo_title_fallback,
            formatArgs = arrayOf(photo.id),
        )
    }

    val missionName = when (photo.mission) {
        Mission.CURIOSITY.apiValue -> stringResource(
            id = R.string.curiosity,
        )

        Mission.INSIGHT.apiValue -> stringResource(
            id = R.string.insight,
        )

        else -> photo.mission
    }

    Column(
        // Long descriptions can extend below the screen, so I allow scrolling
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
        )

        PhotoImage(
            imageUrl = photo.imageUrl,
            description = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 300.dp),
            // Show the whole photograph without cropping its edges
            contentScale = ContentScale.Fit,
        )

        PhotoInformation(
            label = stringResource(id = R.string.photo_detail_id),
            value = photo.id.toString(),
        )

        PhotoInformation(
            label = stringResource(id = R.string.photo_detail_mission),
            value = missionName,
        )

        PhotoInformation(
            label = stringResource(id = R.string.photo_detail_sol),
            value = photo.sol.toString(),
        )

        PhotoInformation(
            label = stringResource(id = R.string.photo_detail_camera),
            value = photo.camera,
        )

        PhotoInformation(
            label = stringResource(id = R.string.photo_detail_date),
            value = photo.dateTaken,
        )

        PhotoInformation(
            label = stringResource(id = R.string.photo_detail_description),
            value = photo.description,
        )

        PhotoInformation(
            label = stringResource(id = R.string.photo_detail_credit),
            value = photo.imageCredit,
        )
    }
}

@Composable
private fun PhotoInformation(
    label: String,
    value: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 4.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            // Missing metadata can be null or blank, so handle both
            text = value?.takeIf { it.isNotBlank() }
                ?: stringResource(id = R.string.photo_detail_unknown),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoDetailContentPreview() {
    MaterialTheme {
        PhotoDetailContent(
            photo = MarsPhoto(
                id = 259238,
                sol = 1000,
                mission = Mission.CURIOSITY.apiValue,
                title = "Sol 1000: Chemistry & Camera",
                camera = "CHEMCAM_RMI",
                imageUrl = "",
                dateTaken = "2015-05-30T12:00:00Z",
                description = "A photograph captured on Mars.",
                imageCredit = null,
            ),
        )
    }
}
