package fr.danersharifi.marslens.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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

@Composable
fun ScreenMessage(
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

        // Request errors can offer retry, empty results need another selection
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
private fun ScreenMessagePreview() {
    MaterialTheme {
        ScreenMessage(
            title = stringResource(id = R.string.photos_error_title),
            message = stringResource(id = R.string.photos_error_message),
            onRetry = {},
        )
    }
}
